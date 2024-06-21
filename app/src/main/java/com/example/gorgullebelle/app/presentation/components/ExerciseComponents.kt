package com.example.gorgullebelle.app.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorgullebelle.R
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



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseCardItem(
    imageRes: Int,
    title: String,
    body: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .clickable { onButtonClick() }
            .width(166.dp)

    ) {
        Column(
            modifier = Modifier
                .background(Color.LightGray)
                .padding(8.dp)
                .width(166.dp)
                //.height(420.dp)
        ) {

            Image(
                painter = painterResource(id = imageRes),
                contentDescription = null,
                modifier = Modifier
                    .height(150.dp)
                    .width(166.dp)
                    .clip(RoundedCornerShape(4.dp))
            )


            Row (
                modifier = Modifier
                    .width(166.dp)

            )
            {
                Text(
                    text = title,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    text = body,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 4.dp)
                )
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewExerciseCardItem() {
    ExerciseCardItem(
        imageRes = R.drawable.konu_tespiti,
        title = "Exercise Title",
        body = "2/41",
        onButtonClick = { /* Do something */ }
    )
}