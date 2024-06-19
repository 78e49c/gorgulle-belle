package com.example.gorgullebelle.app.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
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

    private val _selectedPackageIndex = MutableStateFlow(-1)
    val selectedPackageIndex: StateFlow<Int> = _selectedPackageIndex


    init {
        loadSessions()
    }

    private fun loadSessions() {
        val savedSessions = LocalStorageHelper.loadSessions(context)
        savedSessions.forEach { (id, messages) ->
            sessionMessages[id] = MutableStateFlow(messages)
            Log.d("ChatManagerViewModel", "Session loaded: $id, Messages: $messages")
        }
    }

    fun saveSessions() {
        val sessions = sessionMessages.mapValues { it.value.value }
        LocalStorageHelper.saveSessions(context, sessions)
        Log.d("ChatManagerViewModel", "Sessions saved: $sessions")
    }

    override fun onCleared() {
        super.onCleared()
        saveSessions()
        Log.d("ChatManagerViewModel", "ViewModel cleared and sessions saved.")
    }

    fun handleMessageSend(sessionId: Int, userMessage: String, conversationType: Int) {
        addMessageToSession(sessionId, "You: $userMessage")

        val messageHistory = getSessionMessages(sessionId).value

        val systemMessage = ""
        val assistantMessage = ""

        val messages = MessageBuilder.buildMessageHistory(
            messageHistory.map { Message(it.split(": ")[0], it.split(": ")[1]) },
            userMessage,
            systemMessage,
            assistantMessage
        )

        sendToApi(sessionId, messages, conversationType)
    }

    private fun sendToApi(sessionId: Int, messages: List<Message>, conversationType: Int) {
        repository.sendMessage(context, sessionId, messages, conversationType) { response ->
            addMessageToSession(sessionId, "Bot: $response")
            triggerBotPrompt(sessionId, conversationType, PromptUsageType.ALWAYS)
            saveSessions()
            Log.d("ChatManagerViewModel", "API response received and session saved: $response")
        }
    }

    private fun triggerBotPrompt(sessionId: Int, conversationType: Int, promptUsageType: PromptUsageType) {
        val prompt = when (promptUsageType) {
            PromptUsageType.SYSTEM -> conversationPrompts[conversationType]
            PromptUsageType.ALWAYS -> conversationAlwaysPrompts[conversationType]
        }
        val messageHistory = getSessionMessages(sessionId).value

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
            addMessageToSession(sessionId, "Bot: $response")
            saveSessions()
            Log.d("ChatManagerViewModel", "Bot prompt response received and session saved: $response")
        }
    }

    fun clearSessionMessages(packageIndex: Int) {
        sessionMessages[packageIndex]?.value = emptyList()
        Log.d("ChatManagerViewModel", "Session messages cleared: $packageIndex")
    }

    fun setSelectedPackageIndex(index: Int) {
        _selectedPackageIndex.value = index
        Log.d("ChatManagerViewModel", "Selected package index set: $index")
    }

    fun getSessionMessages(sessionId: Int): StateFlow<List<String>> {
        return sessionMessages.getOrPut(sessionId) { MutableStateFlow(emptyList()) }
    }

    fun getSelectedSessionMessages(): List<String> {
        return sessionMessages[_selectedPackageIndex.value]?.value ?: emptyList()
    }

    fun resetSessionMessages(index: Int) {
        if (index in sessionMessages.keys) {
            sessionMessages[index]?.value = emptyList()
            clearSessionMessages(index)
            Log.d("ChatManagerViewModel", "Session messages reset: $index")
        }
    }

    private fun addMessageToSession(sessionId: Int, message: String) {
        sessionMessages[sessionId]?.update { it + message }
        Log.d("ChatManagerViewModel", "Message added: $message, Session: $sessionId")
    }

    enum class PromptUsageType {
        SYSTEM,
        ALWAYS
    }
}
