package com.example.gorgullebelle.app.presentation.viewmodel

import android.app.Application
import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
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
import kotlinx.coroutines.launch

class ChatManagerViewModel(application: Application) : AndroidViewModel(application) {

    private val context: Context = application.applicationContext
    private val sessionMessages = mutableMapOf<Int, MutableState<List<String>>>()
    private val repository = ApiRepository()

    val currentSessionId = mutableStateOf(0)

    init {
        loadSessions()
    }

    private fun loadSessions() {
        viewModelScope.launch(Dispatchers.IO) {
            val savedSessions = LocalStorageHelper.loadSessions(context)
            savedSessions.forEach { (id, messages) ->
                sessionMessages[id] = mutableStateOf(messages)
            }
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

    fun getMessages(sessionId: Int): MutableState<List<String>> {
        return sessionMessages.getOrPut(sessionId) { mutableStateOf(emptyList()) }
    }

    fun addMessage(sessionId: Int, message: String) {
        val messages = sessionMessages.getOrPut(sessionId) { mutableStateOf(emptyList()) }
        messages.value = messages.value + message
    }

    fun sendMessage(sessionId: Int, userMessage: String, conversationType: Int) {
        addMessage(sessionId, "You: $userMessage")

        val messageHistory = getMessages(sessionId).value ?: emptyList()

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

    fun sendPrompt(sessionId: Int, conversationType: Int, promptUsageType: PromptUsageType, onResponse: ((String) -> Unit)? = null) {
        val prompt = when (promptUsageType) {
            PromptUsageType.SYSTEM -> conversationPrompts[conversationType]
            PromptUsageType.ALWAYS -> conversationAlwaysPrompts[conversationType]
        }
        val messageHistory = getMessages(sessionId).value ?: emptyList()
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
            onResponse?.invoke(response)
        }
    }

    fun updateCurrentSessionId(newSessionId: Int) {
        currentSessionId.value = newSessionId
    }

    fun clearMessages(packageIndex: Int) {
        sessionMessages[packageIndex]?.value = emptyList()
    }

    enum class PromptUsageType {
        SYSTEM,
        ALWAYS
    }
}
