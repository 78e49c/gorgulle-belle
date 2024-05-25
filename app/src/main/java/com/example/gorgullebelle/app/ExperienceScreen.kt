package com.example.gorgullebelle.app

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gorgullebelle.app.navigation.Route
//import kotlinx.coroutines.flow.collectAsState

@Composable
fun ExperienceScreen(navigate: (String) -> Unit = {}) {
    val systemViewModel: System = viewModel()
    var message by remember { mutableStateOf("") }

    // Observe changes in the selected package index
    val selectedPackageIndex by systemViewModel.selectedPackageIndex.collectAsState()
    val messages by remember { derivedStateOf { systemViewModel.messages } }

    Column(modifier = Modifier
        .padding(16.dp)
        .fillMaxSize()) {
        Button(onClick = { navigate(Route.DashboardScreen.route) }) {
            Text(text = "kdskfskı")
        }
        Spacer(modifier = Modifier.height(16.dp))

        Box(modifier = Modifier
            .weight(1f)
            .fillMaxWidth()) {
            MessageList(messages = messages)
        }

        Spacer(modifier = Modifier.height(16.dp))
        Row(modifier = Modifier.fillMaxWidth()) {
            MessageInput(
                message = message,
                onMessageChange = { message = it },
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.width(8.dp))
            Button(onClick = {
                if (message.isNotBlank()) {
                    systemViewModel.addMessage(message)
                    message = ""
                }
            }) {
                Text("Gönder")
            }
        }
    }
}

@Composable
fun MessageList(messages: List<String>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(messages) { msg ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                contentAlignment = Alignment.CenterEnd
            ) {
                Surface(
                    modifier = Modifier
                        .background(Color.LightGray)
                        .padding(8.dp)
                ) {
                    Text(text = msg, fontSize = 18.sp, modifier = Modifier.padding(8.dp))
                }
            }
        }
    }
}

@Composable
fun MessageInput(
    message: String,
    onMessageChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BasicTextField(
        value = message,
        onValueChange = onMessageChange,
        textStyle = TextStyle(fontSize = 18.sp),
        modifier = modifier
            .padding(16.dp)
            .height(56.dp)
            .background(Color.White)
            .padding(8.dp)
    )
}
