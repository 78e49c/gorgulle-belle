package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gorgullebelle.app.data.QuestionRepository
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
import com.example.gorgullebelle.app.presentation.components.QuestionComponent
import com.example.gorgullebelle.app.presentation.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExerciseScreen(
    navigate: (String) -> Unit = {},
) {
    var currentQuestionIndex by remember { mutableStateOf(0) }
    var totalScore by remember { mutableStateOf(0) }
    var selectedChoice by remember { mutableStateOf(-1) }
    val questions = QuestionRepository.getQuestions()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                title = "Konuşma",
                onIconClick = { navigate(Route.ExerciseListScreen.route) }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column(
                modifier = Modifier.fillMaxSize()
            ) {
                if (currentQuestionIndex < questions.size) {
                    val question = questions[currentQuestionIndex]
                    QuestionComponent(
                        question = question,
                        selectedChoice = selectedChoice,
                        onChoiceSelected = { score, index ->
                            totalScore += score
                            selectedChoice = index
                        },
                        onNextQuestion = {
                            currentQuestionIndex++
                            selectedChoice = -1 // Reset selected choice for the next question
                        }
                    )
                } else {
                    Text(
                        text = "Test completed! Your score is: $totalScore",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
