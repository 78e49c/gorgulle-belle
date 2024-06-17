package com.example.gorgullebelle.app.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gorgullebelle.app.data.ChatRequest
import com.example.gorgullebelle.app.data.LocalStorageHelper
import com.example.gorgullebelle.app.data.Message
import com.example.gorgullebelle.app.data.MessageBuilder
import com.example.gorgullebelle.app.data.api.ApiRepository
import com.example.gorgullebelle.app.data.conversationAlwaysPrompts
import com.example.gorgullebelle.app.data.conversationPrompts
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChatManagerViewModel(application: Application) : AndroidViewModel(application) {

    private val context: Context = application.applicationContext
    private val sessionMessages = mutableMapOf<Int, MutableStateFlow<List<String>>>()
    private val repository = ApiRepository()

    private val _currentSessionId = MutableStateFlow(0)
    val currentSessionId: StateFlow<Int> = _currentSessionId

    private val _selectedPackageIndex = MutableStateFlow(0)
    val selectedPackageIndex: StateFlow<Int> = _selectedPackageIndex

    private val _canSendMessages = mutableMapOf<Int, MutableStateFlow<Boolean>>()
    val canSendMessages: StateFlow<Boolean>
        get() = _canSendMessages[_selectedPackageIndex.value] ?: MutableStateFlow(true)

    init {
        loadSessions()
    }

    private fun loadSessions() {
        val savedSessions = LocalStorageHelper.loadSessions(context)
        savedSessions.forEach { (id, messages) ->
            sessionMessages[id] = MutableStateFlow(messages)
            _canSendMessages[id] = MutableStateFlow(true)
        }
    }

    fun saveSessions() {
        val sessions = sessionMessages.mapValues { it.value.value }
        LocalStorageHelper.saveSessions(context, sessions)
    }

    override fun onCleared() {
        super.onCleared()
        saveSessions()
    }

    fun getMessages(sessionId: Int): StateFlow<List<String>> {
        return sessionMessages.getOrPut(sessionId) { MutableStateFlow(emptyList()) }
    }

    fun sendMessage(sessionId: Int, userMessage: String, conversationType: Int) {
        addMessage(sessionId, "You: $userMessage")

        val messageHistory = getMessages(sessionId).value

        val systemMessage = ""
        val assistantMessage = ""

        val messages = MessageBuilder.buildMessageHistory(
            messageHistory.map { Message(it.split(": ")[0], it.split(": ")[1]) },
            userMessage,
            systemMessage,
            assistantMessage
        )

        repository.sendMessage(context, sessionId, messages, conversationType) { response ->
            val updatedMessages = MessageBuilder.buildMessageHistory(
                messages,
                "",
                "",
                response
            )
            addMessage(sessionId, "Bot: $response")
            sendPrompt(sessionId, conversationType, PromptUsageType.ALWAYS)
            saveSessions()
        }
    }

    fun sendPrompt(sessionId: Int, conversationType: Int, promptUsageType: PromptUsageType) {
        val prompt = when (promptUsageType) {
            PromptUsageType.SYSTEM -> conversationPrompts[conversationType]
            PromptUsageType.ALWAYS -> conversationAlwaysPrompts[conversationType]
        }
        val messageHistory = getMessages(sessionId).value

        val messages = MessageBuilder.buildMessageHistory(
            messageHistory.map { Message(it.split(": ")[0], it.split(": ")[1]) },
            "",
            prompt.joinToString(" ") { it.content },
            ""
        )

        val chatRequest = ChatRequest(
            model = "gpt-3.5-turbo",
            messages = messages
        )
        val json = Gson().toJson(chatRequest)
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.sendChatRequest(json)
            val updatedMessages = MessageBuilder.buildMessageHistory(
                messages,
                "",
                "",
                response
            )
            addMessage(sessionId, "Bot: $response")
            saveSessions()
        }
    }

    fun updateCurrentSessionId(newSessionId: Int) {
        _currentSessionId.value = newSessionId
    }

    fun clearMessages(packageIndex: Int) {
        sessionMessages[packageIndex]?.value = emptyList()
    }

    fun setSelectedPackageIndex(index: Int) {
        if (index in sessionMessages.keys) {
            _selectedPackageIndex.value = index
        }
    }

    fun getSelectedMessages(): List<String> {
        return sessionMessages[_selectedPackageIndex.value]?.value ?: emptyList()
    }

    fun resetMessages(index: Int) {
        if (index in sessionMessages.keys) {
            sessionMessages[index]?.value = emptyList()
            _canSendMessages[index]?.value = true
            clearMessages(index)
        }
    }

    fun disableSending(index: Int) {
        if (index in _canSendMessages.keys) {
            _canSendMessages[index]?.value = false
        }
    }

    fun addMessage(sessionId: Int, message: String) {
        sessionMessages[sessionId]?.update { it + message }
    }

    enum class PromptUsageType {
        SYSTEM,
        ALWAYS
    }
}
