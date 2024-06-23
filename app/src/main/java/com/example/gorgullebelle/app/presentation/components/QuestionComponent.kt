package com.example.gorgullebelle.app.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.screen.ExerciseItem

@Composable
fun QuestionComponent(
    key: Int,  // key parametresini ekleyelim
    questionText: String,
    explanation: String,
    choices: List<Pair<String, Int>>,
    onSubmit: (Pair<String, Int>?, Boolean) -> Unit
) {
    var selectedChoice by remember(key) { mutableStateOf<Int?>(null) }
    var isSubmitted by remember(key) { mutableStateOf(false) }

    Surface(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        LazyColumn {
            item {
                Text(
                    text = questionText,
                    style = MaterialTheme.typography.headlineSmall,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
                Text(
                    text = explanation,
                    style = MaterialTheme.typography.labelLarge,
                    modifier = Modifier.padding(bottom = 8.dp)
                )
            }

            itemsIndexed(choices) { index, choice ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(vertical = 4.dp)
                ) {
                    RadioButton(
                        selected = selectedChoice == index,
                        onClick = { selectedChoice = index },
                        colors = RadioButtonDefaults.colors(selectedColor = Color.Black)
                    )
                    Text(
                        text = choice.first,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = {
                        if (isSubmitted) {
                            onSubmit(null, true)  // Already submitted message
                        } else {
                            selectedChoice?.let {
                                onSubmit(choices[it], false)
                                isSubmitted = true
                            } ?: run {
                                onSubmit(null, false)  // No option selected message
                            }
                        }
                    }
                ) {
                    Text("Submit Answer")
                }
            }
        }
    }
}




@Composable
fun ExerciseItemList(
    exerciseItems: List<ExerciseItem>,
    onItemClick: (ExerciseItem) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .padding(8.dp)
    ) {
        if (exerciseItems.isNotEmpty()) {
            item {
                val topItem = exerciseItems[0]
                ExerciseCardItem(
                    imageRes = topItem.imageResId,
                    title = topItem.title,
                    body = topItem.body,
                    onButtonClick = { onItemClick(topItem) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
            items(exerciseItems.drop(1).chunked(2)) { rowItems ->
                Row(modifier = Modifier.padding(8.dp)) {
                    for (item in rowItems) {
                        ExerciseCardItem(
                            imageRes = item.imageResId,
                            title = item.title,
                            body = item.body,
                            onButtonClick = { onItemClick(item) },
                            modifier = Modifier
                                .weight(1f)
                                .padding(4.dp)
                        )
                    }
                }
            }
        }
    }
}




@Composable
fun ExerciseCardItem(
    imageRes: Int,
    title: String,
    body: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        modifier = modifier
            .padding(8.dp)
            .clickable { onButtonClick() }
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .aspectRatio(1f)  // Ensure the box is square
                    .fillMaxWidth()
            ) {
                Image(
                    painter = painterResource(id = imageRes),
                    contentDescription = null,
                    contentScale = ContentScale.Fit,  // Adjust the scale type
                    modifier = Modifier.fillMaxSize()
                )
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(text = title, fontSize = 18.sp)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = body, fontSize = 14.sp, color = Color.Gray)
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


/*
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
*/

