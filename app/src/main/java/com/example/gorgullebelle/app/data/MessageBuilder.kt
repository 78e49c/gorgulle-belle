package com.example.gorgullebelle.app.data

import com.example.gorgullebelle.app.data.dataclass.Message

object MessageBuilder {
    fun buildMessageHistory(
        previousMessages: List<Message>,
        userMessage: String,
        assistantResponse: String?,
        systemMessage: String?
    ): List<Message> {
        val messageHistory = mutableListOf<Message>()

        // Önce önceki mesajları ekleyin
        messageHistory.addAll(previousMessages)

        // Kullanıcı mesajını ekleyin
        messageHistory.add(Message("user", userMessage))

        // Assistant yanıtını ekleyin
        assistantResponse?.let {
            messageHistory.add(Message("assistant", it))
        }

        // System mesajını ekleyin
        systemMessage?.let {
            messageHistory.add(Message("system", it))
        }

        return messageHistory
    }
}

