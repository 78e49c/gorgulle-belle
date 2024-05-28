package com.example.gorgullebelle.app.presentation.viewmodel
import com.example.gorgullebelle.app.data.LocalStorageHelper

import android.app.Application
import android.content.Context
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.MutableState
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatSessionManagerViewModel(application: Application) : AndroidViewModel(application) {

    private val context: Context = application.applicationContext
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
    fun saveSessions() {
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

    // Geçerli oturum kimliğini günceller.
    fun updateCurrentSessionId(newSessionId: Int) {
        currentSessionId.value = newSessionId
    }

    // Belirtilen paket indeksindeki mesajları temizler.
    fun clearMessages(packageIndex: Int) {
        sessionMessages[packageIndex]?.value = emptyList()
    }
}
