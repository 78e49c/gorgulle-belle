package com.example.gorgullebelle.app.data

data class Question(
    val explanation: String,
    val questionText: String,
    val choices: List<Choice>
)