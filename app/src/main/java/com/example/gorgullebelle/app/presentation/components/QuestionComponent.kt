package com.example.gorgullebelle.app.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.app.data.Question

@Composable
fun QuestionComponent(
    question: Question,
    selectedChoice: Int,
    onChoiceSelected: (Int, Int) -> Unit,
    onNextQuestion: () -> Unit
) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = question.explanation)
        Text(text = question.questionText)

        question.choices.forEachIndexed { index, choice ->
            Card(
                modifier = Modifier
                    .padding(vertical = 4.dp)
                    .clickable {
                        onChoiceSelected(choice.score, index)
                    }
            ) {
                Text(
                    text = choice.answer,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }

        if (selectedChoice != -1) {
            Text(
                text = "Next Question",
                modifier = Modifier
                    .padding(top = 16.dp)
                    .clickable {
                        onNextQuestion()
                    }
            )
        }
    }
}
