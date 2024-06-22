package com.example.gorgullebelle.app.presentation.screen

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.viewmodel.QuestionViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun QuestionScreen(
    navigate: (String) -> Unit,
    questionViewModel: QuestionViewModel
) {
    val context = LocalContext.current
    val apiResponse by questionViewModel.apiResponse.observeAsState("")
    val concept by questionViewModel.concept.observeAsState()

    // Concept değeri değiştiğinde fetchQuestion çağrısı
    LaunchedEffect(concept) {
        Log.d("QuestionScreen", "LaunchedEffect called with concept: $concept")
        concept?.let {
            questionViewModel.fetchQuestion(context)
        }
    }

    // API response'u log olarak yazdırma
    Log.d("QuestionScreen", "API Response: $apiResponse")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Spacer(modifier = Modifier.height(16.dp))

            // API response'u gösterme
            Text(
                text = apiResponse,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Yeni soru çekmek için buton
            IconButton(
                onClick = {
                    Log.d("QuestionScreen", "Refresh button clicked")
                    questionViewModel.fetchQuestion(context)
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
}
