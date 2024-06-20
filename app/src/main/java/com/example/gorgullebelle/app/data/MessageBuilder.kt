package com.example.gorgullebelle.app.data

object MessageBuilder {
    // Mesaj geçmişine kullanıcı, sistem ve asistan mesajlarını ekler
    fun buildMessageHistory(messageHistory: List<Message>, userMessage: String?, systemMessage: String?, assistantMessage: String?): List<Message> {
        val updatedMessages = messageHistory.toMutableList()

        // Sistem mesajı boş değilse, listeye ekle
        systemMessage?.let {
            updatedMessages.add(Message("system", it))
        }

        // Kullanıcı mesajı boş değilse, listeye ekle
        userMessage?.let {
            updatedMessages.add(Message("user", it))
        }

        // Asistan mesajı boş değilse, listeye ekle
        assistantMessage?.let {
            updatedMessages.add(Message("assistant", it))
        }

        return updatedMessages
    }
}
