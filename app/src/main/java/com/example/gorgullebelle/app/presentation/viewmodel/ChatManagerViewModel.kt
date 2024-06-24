package com.example.gorgullebelle.app.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import com.example.gorgullebelle.app.data.ApiRepository
import com.example.gorgullebelle.app.data.LocalStorageHelper
import com.example.gorgullebelle.app.data.MessageBuilder
import com.example.gorgullebelle.app.data.conversationPrompts
import com.example.gorgullebelle.app.data.dataclass.Message
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

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

    fun handleMessageSend(sessionId: Int, userMessage: String) {
        addMessageToSession(sessionId, "user: $userMessage")

        val messageHistory = getSessionMessages(sessionId).value

        val messages = MessageBuilder.buildMessageHistory(
            messageHistory.map { Message(it.split(": ")[0], it.split(": ")[1]) },
            userMessage,
            null,
            null
        )

        // Tek seferde mesaj göndermek için sendToApi fonksiyonunu çağır
        sendToApi(sessionId, messages)
    }

    private fun sendToApi(sessionId: Int, messages: List<Message>) {
        Log.d("ChatManagerViewModel", "Sending messages to API: $messages")
        repository.sendMessage(context, sessionId, messages) { response ->
            addMessageToSession(sessionId, "assistant: $response")
            saveSessions()
            Log.d("ChatManagerViewModel", "API response received and session saved: $response")
        }
    }

    fun clearSessionMessages(packageIndex: Int) {
        val prompts = conversationPrompts[packageIndex] ?: listOf()
        sessionMessages[packageIndex]?.value = emptyList()
        prompts.forEach { prompt ->
            addMessageToSession(packageIndex, "system: ${prompt.content}")
        }
        Log.d("ChatManagerViewModel", "Session messages cleared and prompts added: $packageIndex")
    }

    fun setSelectedPackageIndex(index: Int) {
        _selectedPackageIndex.value = index
        Log.d("ChatManagerViewModel", "Selected package index set: $index")
    }

    fun getSessionMessages(sessionId: Int): StateFlow<List<String>> {
        return sessionMessages.getOrPut(sessionId) { MutableStateFlow(emptyList()) }
    }


    private fun addMessageToSession(sessionId: Int, message: String) {
        sessionMessages.getOrPut(sessionId) { MutableStateFlow(emptyList()) }.update { it + message }
        Log.d("ChatManagerViewModel", "Message added to session: $sessionId, Message: $message")
    }

    fun getPromptForPackage(packageIndex: Int): List<String> {
        val prompts = conversationPrompts[packageIndex] ?: return emptyList()
        return prompts.map { it.content }
    }
}
