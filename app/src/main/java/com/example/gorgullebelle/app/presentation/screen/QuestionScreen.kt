package com.example.gorgullebelle.app.presentation.screen

import android.util.Log
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.app.data.Question
import com.example.gorgullebelle.app.presentation.components.InfoComponent
import com.example.gorgullebelle.app.presentation.components.QuestionComponent
import com.example.gorgullebelle.app.presentation.viewmodel.ProfileViewModel
import com.example.gorgullebelle.app.presentation.viewmodel.QuestionViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterialApi::class)
@Composable
fun QuestionScreen(
    navigate: (String) -> Unit,
    questionViewModel: QuestionViewModel,
    profileViewModel: ProfileViewModel
) {
    val context = LocalContext.current
    val apiResponse by questionViewModel.apiResponse.observeAsState("")
    val concept by questionViewModel.concept.observeAsState()
    var question by remember { mutableStateOf<Question?>(null) }
    var errorMessage by remember { mutableStateOf<String?>(null) }
    var key by remember { mutableStateOf(0) }
    var snackbarMessage by remember { mutableStateOf<String?>(null) }
    var isLoading by remember { mutableStateOf(true) }

    val swipeableState = rememberSwipeableState(initialValue = 0)
    val scope = rememberCoroutineScope()
    val anchors = mapOf(0f to 0, 300f to 1) // You can adjust the swipe distance as needed

    LaunchedEffect(concept) {
        Log.d("QuestionScreen", "LaunchedEffect called with concept: $concept")
        concept?.let {
            isLoading = true
            questionViewModel.fetchQuestion(context)
        }
    }

    Log.d("QuestionScreen", "API Response: $apiResponse")

    LaunchedEffect(apiResponse) {
        try {
            question = questionViewModel.parseApiResponse(apiResponse)
            errorMessage = if (question == null) apiResponse else null
        } catch (e: Exception) {
            errorMessage = apiResponse
        } finally {
            isLoading = false
        }
    }

    Surface(
        modifier = Modifier
            .fillMaxSize()
            .swipeable(
                state = swipeableState,
                anchors = anchors,
                thresholds = { _, _ -> FractionalThreshold(0.3f) },
                orientation = Orientation.Horizontal,
                reverseDirection = true
            ),
        color = Color.White
    ) {
        if (isLoading) {
            InfoComponent("Soru uyduruluyor")
        } else if (errorMessage != null) {
            InfoComponent("Bazen böyle oluyor yeni soruya geç")
        } else if (question != null) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Spacer(modifier = Modifier.height(16.dp))

                QuestionComponent(
                    key = key,
                    questionText = question!!.questionText,
                    explanation = question!!.explanation,
                    choices = question!!.choices.map { choice ->
                        choice.answer to choice.score
                    },
                    onSubmit = { selectedChoice, isSubmitted ->
                        if (isSubmitted) {
                            snackbarMessage = "You have already submitted an answer."
                        } else {
                            if (selectedChoice != null) {
                                profileViewModel.updateScore(selectedChoice.second)
                                snackbarMessage = "You scored ${selectedChoice.second} points!"
                            } else {
                                snackbarMessage = "Please select an option."
                            }
                        }
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }

    snackbarMessage?.let { message ->
        LaunchedEffect(message) {
            delay(1000)
            snackbarMessage = null
        }
        Snackbar(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = message)
        }
    }

    LaunchedEffect(swipeableState.currentValue) {
        if (swipeableState.currentValue == 1) {
            Log.d("QuestionScreen", "Screen swiped to refresh")
            isLoading = true
            questionViewModel.fetchQuestion(context)
            key++
            scope.launch {
                swipeableState.snapTo(0)
            }
        }
    }
}











