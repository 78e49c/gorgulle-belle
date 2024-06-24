package com.example.gorgullebelle.app.data.dataclass

data class Question(
    val explanation: String,
    val questionText: String,
    val choices: List<Choice>
)