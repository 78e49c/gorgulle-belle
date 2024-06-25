package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.app.presentation.components.AssistantMessageBubble
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
import com.example.gorgullebelle.app.presentation.components.MessageInput
import com.example.gorgullebelle.app.presentation.components.SystemMessageBubble
import com.example.gorgullebelle.app.presentation.components.UserMessageBubble
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.ChatManagerViewModel
import com.example.gorgullebelle.app.presentation.viewmodel.ProfileViewModel
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationScreen(
    chatManagerViewModel: ChatManagerViewModel,
    profileViewModel: ProfileViewModel,
    navigate: (String) -> Unit
) {
    val selectedPackageIndex by chatManagerViewModel.selectedPackageIndex.collectAsState()
    val messagesState by chatManagerViewModel.getSessionMessages(selectedPackageIndex).collectAsState(emptyList())
    val systemMessageVisible by profileViewModel.systemMessageVisible.observeAsState(false)
    val conversationTitle = when (selectedPackageIndex) {
        0 -> "AI Dedektifi"
        1 -> "Gönül Hasbihali"
        2 -> "Şam Yolculuğu"
        else -> "Konuşma"
    }

    var expanded by remember { mutableStateOf(false) }
    val listState = rememberLazyListState()

    LaunchedEffect(messagesState) {
        if (messagesState.isNotEmpty()) {
            listState.animateScrollToItem(messagesState.size - 1)
        }
    }

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                CustomTopAppBar(
                    conversationTitle = conversationTitle,
                    onBackPressed = { navigate(Route.ConversationListScreen.route) },
                    menuContent = {
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
                                    profileViewModel.updateScore(50)
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
                        state = listState,
                        modifier = Modifier
                            .weight(1f)
                            .padding(8.dp)
                    ) {
                        items(messagesState) { message ->
                            val messageText = message.substringAfter(": ")
                            val timestamp = SimpleDateFormat("HH:mm", Locale.getDefault()).format(Date())
                            when {
                                message.startsWith("user:") -> UserMessageBubble(messageText, timestamp)
                                message.startsWith("assistant:") -> AssistantMessageBubble(messageText, timestamp)
                                message.startsWith("system:") -> SystemMessageBubble(messageText, timestamp, systemMessageVisible)
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
