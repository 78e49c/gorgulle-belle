package com.example.gorgullebelle.app.data

data class Question(
    val concept: String,
    val topic: String = "General",
    val explanation: String,
    val questionText: String,
    val choices: List<Choice>
)