package com.example.gorgullebelle.app.data
object MessageBuilder {
    // Mesaj geçmişine kullanıcı, sistem ve asistan mesajlarını ekler
    fun buildMessageHistory(messageHistory: List<Message>, userMessage: String, systemMessage: String, assistantMessage: String): List<Message> {
        val updatedMessages = messageHistory.toMutableList()

        // Sistem mesajı boş değilse, listeye ekle
        if (systemMessage.isNotEmpty()) {
            updatedMessages.add(Message("system", systemMessage))
        }

        // Kullanıcı mesajı boş değilse, listeye ekle
        if (userMessage.isNotEmpty()) {
            updatedMessages.add(Message("user", userMessage))
        }

        // Asistan mesajı boş değilse, listeye ekle
        if (assistantMessage.isNotEmpty()) {
            updatedMessages.add(Message("assistant", assistantMessage))
        }

        return updatedMessages
    }
}