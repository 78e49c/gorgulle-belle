package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.ChatManagerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperienceScreen(
    chatManagerViewModel: ChatManagerViewModel = viewModel(),
    navigate: (String) -> Unit = {}
) {
    val selectedPackageIndex by chatManagerViewModel.selectedPackageIndex.collectAsState()
    val messagesState by chatManagerViewModel.getSessionMessages(selectedPackageIndex).collectAsState()

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CustomTopAppBar(
                    title = "",
                    onIconClick = { navigate(Route.ConversationListScreen.route) }
                )
            }
        ) { innerPadding ->
            Box(modifier = Modifier.padding(innerPadding)) {
                Column(
                    modifier = Modifier.fillMaxSize()
                ) {

                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        items(messagesState) { message ->
                            if (message.startsWith("Bot:")) {
                                BotMessageBubble(message = message)
                            } else {
                                UserMessageBubble(message = message)
                            }
                        }
                    }

                    MessageInput(
                        onSend = { messageText ->
                            chatManagerViewModel.handleMessageSend(selectedPackageIndex, messageText, 2)
                        }
                    )
                }
            }
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
