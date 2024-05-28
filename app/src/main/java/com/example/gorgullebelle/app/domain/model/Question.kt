package com.example.gorgullebelle.app.domain.model

data class Choice(
    val text: String,
    val score: Int
)

data class Question(
    val text: String,
    val choices: List<Choice>
)
