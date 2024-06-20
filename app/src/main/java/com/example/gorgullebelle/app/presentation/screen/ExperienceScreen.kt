package com.example.gorgullebelle.app.presentation.screen

import android.util.Log
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.ChatManagerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ExperienceScreen(
    chatManagerViewModel: ChatManagerViewModel,
    navigate: (String) -> Unit = {}
) {
    val selectedPackageIndex by chatManagerViewModel.selectedPackageIndex.collectAsState()
    Log.d("ExperienceScreen", "Seçili Paket Indexi: $selectedPackageIndex")
    val messagesState by chatManagerViewModel.getSessionMessages(selectedPackageIndex).collectAsState()
    Log.d("ExperienceScreen", "Mesajlar: ${messagesState.joinToString(", ")}")

    var expanded by remember { mutableStateOf(false) }

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text("") },
                    navigationIcon = {
                        IconButton(onClick = { navigate(Route.ConversationListScreen.route) }) {
                            Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                        }
                    },
                    actions = {
                        IconButton(onClick = { expanded = !expanded }) {
                            Icon(Icons.Default.MoreVert, contentDescription = "Menu")
                        }
                        DropdownMenu(
                            expanded = expanded,
                            onDismissRequest = { expanded = false }
                        ) {
                            DropdownMenuItem(
                                text = { Text("Konuşmayı Sil") },
                                onClick = {
                                    chatManagerViewModel.clearSessionMessages(selectedPackageIndex)
                                    expanded = false
                                }
                            )
                        }
                    }
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
                            when {
                                message.startsWith("user:") -> UserMessageBubble(message.removePrefix("user: "))
                                message.startsWith("assistant:") -> AssistantMessageBubble(message.removePrefix("assistant: "))
                                message.startsWith("system:") -> SystemMessageBubble(message.removePrefix("system: "))
                            }
                        }
                    }
                    MessageInput(
                        onSend = { messageText ->
                            chatManagerViewModel.handleMessageSend(selectedPackageIndex, messageText)
                        }
                    )
                }
            }
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
            modifier = Modifier.widthIn(max = 250.dp),
            colors = CardDefaults.cardColors(colorResource(id = R.color.user_color))
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(8.dp),
                color = Color.White
            )
        }
    }
}

@Composable
fun AssistantMessageBubble(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Card(
            modifier = Modifier.widthIn(max = 250.dp),
            colors = CardDefaults.cardColors(colorResource(id = R.color.assistant_color))
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(8.dp),
                color = Color.White
            )
        }
    }
}

@Composable
fun SystemMessageBubble(message: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier.widthIn(max = 250.dp),
            colors = CardDefaults.cardColors(colorResource(id = R.color.system_color))
        ) {
            Text(
                text = message,
                modifier = Modifier.padding(8.dp),
                color = Color.White
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
                    Log.d("MessageInput", "Mesaj Gönderiliyor: $messageText")
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
