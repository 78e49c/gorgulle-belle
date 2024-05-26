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

    // Oturum mesajlarını depolar.
    private val sessionMessages = mutableMapOf<Int, MutableState<List<String>>>()
    var currentSessionId = mutableStateOf(0)
        private set

    init {
        loadSessions()
    }

    // Oturumları cihazdan yükler.
    private fun loadSessions() {
        viewModelScope.launch(Dispatchers.IO) {
            val savedSessions = LocalStorageHelper.loadSessions(context)
            savedSessions.forEach { (id, messages) ->
                sessionMessages[id] = mutableStateOf(messages)
            }
        }
    }

    // Oturumları cihaza kaydeder.
    private fun saveSessions() {
        val sessions = sessionMessages.mapValues { it.value.value }
        LocalStorageHelper.saveSessions(context, sessions)
    }

    // ViewModel temizlendiğinde oturumları kaydeder.
    override fun onCleared() {
        super.onCleared()
        saveSessions()
    }

    // Belirtilen oturum için mesajları getirir.
    fun getMessages(sessionId: Int): MutableState<List<String>> {
        return sessionMessages.getOrPut(sessionId) { mutableStateOf(emptyList()) }
    }

    // Belirtilen oturuma mesaj ekler.
    fun addMessage(sessionId: Int, message: String) {
        val messages = sessionMessages.getOrPut(sessionId) { mutableStateOf(emptyList()) }
        messages.value = messages.value + message
    }

    // Mesaj gönderir ve cevabı ekler.
    fun sendMessage(sessionId: Int, message: String) {
        addMessage(sessionId, "You: $message")
        repository.sendMessage(context, sessionId, message) { response ->
            addMessage(sessionId, "Bot: $response")
            saveSessions()
        }
    }

    // Geçerli oturum kimliğini günceller.
    fun updateCurrentSessionId(newSessionId: Int) {
        currentSessionId.value = newSessionId
    }

    // Belirtilen paket indeksindeki mesajları temizler.
    fun clearMessages(packageIndex: Int) {
        sessionMessages[packageIndex]?.value = emptyList()
    }
}
