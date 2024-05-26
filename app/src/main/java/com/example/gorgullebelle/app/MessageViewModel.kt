package com.example.gorgullebelle.app

import android.app.Application
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.MutableState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MessageViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ApiRepository()
    private val context = application.applicationContext

    private val sessionMessages = mutableMapOf<Int, MutableState<List<String>>>()
    var currentSessionId = mutableStateOf(0)
        private set

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

    private fun saveSessions() {
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

    fun sendMessage(sessionId: Int, message: String) {
        addMessage(sessionId, "You: $message")
        repository.sendMessage(context, sessionId, message) { response ->
            addMessage(sessionId, "Bot: $response")
            saveSessions()  // Her yeni mesajdan sonra oturumları kaydet
        }
    }

    fun updateCurrentSessionId(newSessionId: Int) {
        currentSessionId.value = newSessionId
    }
}
