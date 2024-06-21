package com.example.gorgullebelle.app.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorgullebelle.app.domain.model.Question

@Composable
fun ExerciseListItem(title: String, imageResId: Int, onClick: () -> Unit) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier.size(60.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyMedium.copy(fontSize = 24.sp))
            // Spacer(modifier = Modifier.height(4.dp))
            // Text(text = "asd", style = MaterialTheme.typography.bodyMedium)
        }
    }
}

@Composable
fun QuestionComponent(
    question: Question,
    selectedChoice: Int,
    onChoiceSelected: (Int, Int) -> Unit,
    onNextQuestion: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Text(
            text = question.text,
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        question.choices.forEachIndexed { index, choice ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        onChoiceSelected(choice.score, index)
                    }
                    .padding(vertical = 4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                RadioButton(
                    selected = (selectedChoice == index),
                    onClick = {
                        onChoiceSelected(choice.score, index)
                    }
                )
                Text(text = choice.text, fontSize = 16.sp)
            }
        }

        // Button to move to the next question
        //Spacer(modifier = Modifier.height(16.dp))
        Spacer(modifier = Modifier.weight(0.2f))

        Row {
            Spacer(modifier = Modifier.weight(0.5f))
            Button( onClick = { onNextQuestion() }) {
                Text(text = "Sonraki soru")
            }
        }

    }
}