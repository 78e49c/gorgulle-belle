package com.example.gorgullebelle.app.data

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.gorgullebelle.app.presentation.viewmodel.ChatSessionManagerViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ChatMessageHandlerViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = ApiRepository()
    private val context = application.applicationContext
    private val chatSessionManagerViewModel = ChatSessionManagerViewModel(application)

    // Mesaj gönderir ve cevabı ekler.
    fun sendMessage(sessionId: Int, userMessage: String, conversationType: Int) {
        // Kullanıcı mesajını ekler
        chatSessionManagerViewModel.addMessage(sessionId, "You: $userMessage")

        // Geçmiş mesajları al
        val messageHistory = chatSessionManagerViewModel.getMessages(sessionId).value ?: emptyList()

        // Sistem mesajı (ALWAYS prompt) boş olabilir
        val systemMessage = ""
        val assistantMessage = ""

        // Mesajları oluştur
        val messages = MessageBuilder.buildMessageHistory(
            messageHistory.map { Message(it.split(": ")[0], it.split(": ")[1]) },
            userMessage,
            systemMessage,
            assistantMessage
        )

        // API'ye istek gönderir ve yanıtı ekler
        repository.sendMessage(context, sessionId, messages, conversationType) { response ->
            val updatedMessages = MessageBuilder.buildMessageHistory(
                messages,
                "",
                "",
                response
            )
            chatSessionManagerViewModel.addMessage(sessionId, "Bot: $response")
            sendPrompt(sessionId, conversationType, PromptUsageType.ALWAYS)
            chatSessionManagerViewModel.saveSessions()
        }
    }

    // Prompt gönderir ve cevabı ekler.
    // Bu fonksiyon hem sistem promptunu hem de always promptunu yönetir.
    fun sendPrompt(sessionId: Int, conversationType: Int, promptUsageType: PromptUsageType, onResponse: ((String) -> Unit)? = null) {
        val prompt = when (promptUsageType) {
            PromptUsageType.SYSTEM -> conversationPrompts[conversationType]
            PromptUsageType.ALWAYS -> conversationAlwaysPrompts[conversationType]
        }
        val messageHistory = chatSessionManagerViewModel.getMessages(sessionId).value ?: emptyList()
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
            chatSessionManagerViewModel.addMessage(sessionId, "Bot: $response")
            chatSessionManagerViewModel.saveSessions()
            onResponse?.invoke(response)
        }
    }

    // Prompt türlerini temsil eden enum sınıfı.
    enum class PromptUsageType {
        SYSTEM,
        ALWAYS
    }
}
