package com.example.gorgullebelle.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gorgullebelle.navigation.Route
import com.example.gorgullebelle.app.MessageViewModel
import com.example.gorgullebelle.app.System

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperienceScreen(
    messageViewModel: MessageViewModel,
    navigate: (String) -> Unit = {}) {

    val selectedPackageIndex by System.selectedPackageIndex.collectAsState()
    val messagesState = messageViewModel.getMessages(selectedPackageIndex)
    val messages by messagesState
    val canSendMessages by System.getCanSendMessages(selectedPackageIndex).collectAsState()

    var shouldRecompose by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Button(onClick = { navigate(Route.DashboardScreen.route) }) {
            Text(text = "Menü")
        }

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .padding(8.dp)
        ) {
            items(messages) { message ->
                if (message.startsWith("Bot:")) {
                    BotMessageBubble(message = message)
                } else {
                    UserMessageBubble(message = message)
                }
            }
        }

        if (canSendMessages) {
            MessageInput(
                onSend = { messageText ->
                    messageViewModel.sendMessage(selectedPackageIndex, messageText)
                }
            )
        } else {
            Button(
                onClick = {
                    System.resetMessages(selectedPackageIndex, messageViewModel)
                    shouldRecompose = true // Ekranın yeniden yüklenmesini tetikle
                },
                modifier = Modifier.align(Alignment.End).padding(8.dp)
            ) {
                Text(text = "Reset")
            }
        }
    }

    // Ekranın yeniden yüklenmesini tetiklemek için
    if (shouldRecompose) {
        LaunchedEffect(Unit) {
            shouldRecompose = false
        }
    }
}

@Composable
fun BotMessageBubble(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Card(
            modifier = Modifier.widthIn(max = 250.dp),
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(8.dp),
                color = LocalContentColor.current
            )
        }
    }
}

@Composable
fun UserMessageBubble(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Card(
            modifier = Modifier.widthIn(max = 250.dp)
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(8.dp),
                color = LocalContentColor.current
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInput(onSend: (String) -> Unit) {
    var messageText by remember { mutableStateOf("") }

    Row(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = messageText,
            onValueChange = { messageText = it },
            modifier = Modifier.weight(1f),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send,
                keyboardType = KeyboardType.Text
            ),
            singleLine = true,
            placeholder = { Text(text = "Mesajınızı yazın...") }
        )
        Button(
            onClick = {
                if (messageText.isNotBlank()) {
                    onSend(messageText)
                    messageText = ""
                }
            },
            modifier = Modifier.padding(start = 8.dp)
        ) {
            Text("Gönder")
        }
    }
}
