package com.example.gorgullebelle.app.data


import com.example.gorgullebelle.app.domain.model.Choice
import com.example.gorgullebelle.app.domain.model.Question

object QuestionRepository {
    fun getQuestions(): List<Question> {
        return listOf(
            Question(
                text = "\t\n" +
                        "Acraea terpsicore, commonly known as the tawny coster, is a species of butterfly in the Nymphalidae family, the brush-footed butterflies. It is found across eastern Asia from India and Sri Lanka to Singapore, Indonesia and the Maldives and, more recently, Australia.",
                choices = listOf(
                    Choice(text = "Böcek", score = 1),
                    Choice(text = "Bitki", score = -1),
                    Choice(text = "Kelebek", score = 3),
                    Choice(text = "Hindistan", score = -1)
                )
            ),
            Question(
                text = "İkinci test sorusu. Doğru cevabı seçiniz.",
                choices = listOf(
                    Choice(text = "Seçenek A", score = -2),
                    Choice(text = "Seçenek B", score = -1),
                    Choice(text = "Seçenek C", score = 1),
                    Choice(text = "Seçenek D", score = 2)
                )
            )
            // Add more questions as needed
        )
    }
}
