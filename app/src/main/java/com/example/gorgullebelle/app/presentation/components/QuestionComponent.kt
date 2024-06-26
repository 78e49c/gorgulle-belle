package com.example.gorgullebelle.app.presentation.components


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.screen.ExerciseItem
import com.example.gorgullebelle.app.presentation.viewmodel.ProfileViewModel

@Composable
fun QuestionComponent(
    key: Int,
    questionText: String,
    explanation: String,
    choices: List<Pair<String, Int>>,
    onSubmit: (Pair<String, Int>?, Boolean) -> Unit
) {
    var selectedChoice by remember(key) { mutableStateOf<Int?>(null) }
    var isSubmitted by remember(key) { mutableStateOf(false) }

    val submit = stringResource(R.string.submit)

    Column(
        modifier = Modifier
            .padding(16.dp)
            .fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                item {
                    Text(
                        text = questionText,
                        style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.Bold),
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                    Text(
                        text = explanation,
                        style = MaterialTheme.typography.bodyLarge,
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
            }
        }

        QuestionButtonComponent(
            value = submit,
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
            },

        )


    }
}



@Composable
fun QuestionButtonComponent(value: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .heightIn(48.dp),
        contentPadding = PaddingValues(),
        colors = ButtonDefaults.buttonColors(Color.Transparent)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(48.dp)
                .background(
                    brush = Brush.horizontalGradient(
                        listOf(
                            colorResource(id = R.color.black),
                            colorResource(id = R.color.black)
                        )
                    ),
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Composable
fun InfoComponent(message: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(12.dp),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = message,
            style = MaterialTheme.typography.displaySmall,
            color = Color.Black,
            textAlign = TextAlign.Center
        )
    }
}






@Composable
fun QuestionItemList(
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
                QuestionCardItem(
                    imageRes = topItem.imageResId,
                    title = topItem.title,
                    onButtonClick = { onItemClick(topItem) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
            items(exerciseItems.drop(1).chunked(2)) { rowItems ->
                Row(modifier = Modifier.padding(8.dp)) {
                    for (item in rowItems) {
                        QuestionCardItem(
                            imageRes = item.imageResId,
                            title = item.title,
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
fun QuestionCardItem(
    imageRes: Int,
    title: String,
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(

        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(containerColor = colorResource(id = R.color.card_color)),
        modifier = modifier
            .padding(8.dp)
            .clickable { onButtonClick() }
            .border(2.dp,(colorResource(id = R.color.card_border_color)),shape = RoundedCornerShape(8.dp))
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
        }
    }
}


@Composable
fun TopicSelectionDialog(
    profileViewModel: ProfileViewModel = viewModel(),
    onDismissRequest: () -> Unit,
    onTopicSelected: (Int) -> Unit
) {
    val topics by profileViewModel.topics.observeAsState(emptyList())
    var selectedTopicIndex by remember { mutableStateOf<Int?>(null) }

    Dialog(onDismissRequest = onDismissRequest) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            modifier = Modifier.padding(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .height(300.dp)
                    .padding(16.dp)
            ) {
                Text(
                    text = "Bir Konu Seçin",
                    style = MaterialTheme.typography.bodyMedium,
                    modifier = Modifier.padding(bottom = 16.dp)
                )
                LazyColumn(
                    modifier = Modifier.weight(1f)
                ) {
                    itemsIndexed(topics) { index, topic ->
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                                .clickable { selectedTopicIndex = index }
                        ) {
                            RadioButton(
                                selected = selectedTopicIndex == index,
                                onClick = { selectedTopicIndex = index }
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = topic)
                        }
                    }
                }
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 16.dp)
                ) {
                    Button(
                        onClick = {
                            selectedTopicIndex?.let { onTopicSelected(it) }
                            onDismissRequest()
                        },
                        enabled = selectedTopicIndex != null
                    ) {
                        Text("Seç")
                    }
                }
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun PreviewExerciseCardItem() {
    QuestionCardItem(
        imageRes = R.drawable.konu_tespiti,
        title = "Exercise Title",
        onButtonClick = { /* Do something */ }
    )
}

