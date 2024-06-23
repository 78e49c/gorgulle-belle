package com.example.gorgullebelle.app.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.data.Choice
import com.example.gorgullebelle.app.data.Question
import com.example.gorgullebelle.app.presentation.components.QuestionComponent
import com.example.gorgullebelle.app.presentation.viewmodel.ProfileViewModel
import com.example.gorgullebelle.app.presentation.viewmodel.QuestionViewModel
import kotlinx.coroutines.delay

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
    var key by remember { mutableStateOf(0) }  // Key to reset QuestionComponent
    var snackbarMessage by remember { mutableStateOf<String?>(null) }

    // Concept değeri değiştiğinde fetchQuestion çağrısı
    LaunchedEffect(concept) {
        Log.d("QuestionScreen", "LaunchedEffect called with concept: $concept")
        concept?.let {
            questionViewModel.fetchQuestion(context)
        }
    }

    // API response'u log olarak yazdırma
    Log.d("QuestionScreen", "API Response: $apiResponse")

    LaunchedEffect(apiResponse) {
        try {
            question = parseApiResponse(apiResponse)
            errorMessage = if (question == null) apiResponse else null
        } catch (e: Exception) {
            errorMessage = apiResponse
        }
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            if (errorMessage != null) {
                Text(
                    text = errorMessage ?: "",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red
                )
            } else if (question != null) {
                QuestionComponent(
                    key = key,  // Pass the key here
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
            }

            Spacer(modifier = Modifier.height(16.dp))

            // Yeni soru çekmek için buton
            IconButton(
                onClick = {
                    Log.d("QuestionScreen", "Refresh button clicked")
                    questionViewModel.fetchQuestion(context)
                    key++  // Increment the key to reset QuestionComponent
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.Refresh,
                    contentDescription = stringResource(id = R.string.refresh_button),
                    tint = Color.Black
                )
            }
        }
    }

    snackbarMessage?.let { message ->
        LaunchedEffect(message) {
            delay(1000)  // Snackbar will disappear after 1 second
            snackbarMessage = null
        }
        Snackbar(
            modifier = Modifier.padding(8.dp)
        ) {
            Text(text = message)
        }
    }
}









fun parseApiResponse(apiResponse: String): Question? {
    val explanationPattern = Regex("""explanation:\s*"([^"]*)"""", RegexOption.DOT_MATCHES_ALL)
    val mainTextPattern = Regex("""mainText:\s*"([^"]*)"""", RegexOption.DOT_MATCHES_ALL)
    val answerScorePattern = Regex("""answer(\d+):\s*"([^"]*)"\s*score\1:\s*"([^"]*)"""", RegexOption.DOT_MATCHES_ALL)

    val explanationMatch = explanationPattern.find(apiResponse)
    val mainTextMatch = mainTextPattern.find(apiResponse)
    val answerScoreMatches = answerScorePattern.findAll(apiResponse)

    if (explanationMatch != null && mainTextMatch != null && answerScoreMatches.count() == 4) {
        val explanation = explanationMatch.groupValues[1].trim()
        val mainText = mainTextMatch.groupValues[1].trim()
        val choices = answerScoreMatches.map { match ->
            Choice(match.groupValues[2].trim(), match.groupValues[3].trim().toInt())
        }.toList()

        return Question(explanation, mainText, choices)
    }

    return null
}
