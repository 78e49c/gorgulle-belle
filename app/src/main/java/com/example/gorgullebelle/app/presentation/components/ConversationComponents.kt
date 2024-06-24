package com.example.gorgullebelle.app.presentation.components

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.ChatManagerViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@Composable
fun ConversationListItem(
    title: String,
    imageResId: Int,
    sessionId: Int,
    chatManagerViewModel: ChatManagerViewModel,
    navigate: (String) -> Unit
) {
    val messagesFlow = chatManagerViewModel.getSessionMessages(sessionId).collectAsState()
    val lastMessage = messagesFlow.value.lastOrNull()?.removePrefix("user: ")?.removePrefix("assistant: ")?.removePrefix("system: ")
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                Log.d("ConversationListItem", "Clicked: $sessionId")
                chatManagerViewModel.setSelectedPackageIndex(sessionId)
                navigate(Route.ExperienceScreen.route)
            }
            .padding(8.dp),
        horizontalArrangement = Arrangement.Start
    ) {
        Image(
            painter = painterResource(id = imageResId),
            contentDescription = null,
            modifier = Modifier.size(128.dp)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = title, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = lastMessage?.take(100) ?: "No messages yet",
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}


@Composable
fun UserMessageBubble(message: String, timestamp: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        contentAlignment = Alignment.CenterEnd
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 250.dp)
            ,elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

        ) {

            Column (
                Modifier
                    .clip(RoundedCornerShape(8.dp))
                    //.fillMaxSize()
                    .background(colorResource(id = R.color.user_color))
                    .padding(8.dp)
            ) {

                Text(
                    text = message,
                    color = Color.White
                )
                Text(
                    text = timestamp,
                    fontSize = 10.sp,
                    color = Color.LightGray,
                    modifier = Modifier.align(Alignment.End)
                )
            }


        }
    }
}

@Composable
fun AssistantMessageBubble(message: String, timestamp: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 250.dp)
            ,elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

        ) {

            Column (
                Modifier
                    .clip(RoundedCornerShape(8.dp))
                    //.fillMaxSize()
                    .background(colorResource(id = R.color.assistant_color))
                    .padding(8.dp)
            ) {

                Text(
                    text = message,
                    color = Color.White
                )
                Text(
                    text = timestamp,
                    fontSize = 10.sp,
                    color = Color.LightGray,
                    modifier = Modifier.align(Alignment.End)
                )
            }


        }
    }
}

@Composable
fun SystemMessageBubble(message: String, timestamp: String) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .widthIn(max = 250.dp)
            ,elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)

        ) {

            Column (
                Modifier
                    .clip(RoundedCornerShape(8.dp))
                    //.fillMaxSize()
                    .background(colorResource(id = R.color.system_color))
                    .padding(8.dp)
            ) {

                Text(
                    text = message,
                    color = Color.White
                )
                Text(
                    text = timestamp,
                    fontSize = 10.sp,
                    color = Color.LightGray,
                    modifier = Modifier.align(Alignment.End)
                )
            }


        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessageInput(onSend: (String) -> Unit) {
    var messageText by remember { mutableStateOf("") }
    var isSending by remember { mutableStateOf(false) }
    val coroutineScope = rememberCoroutineScope()

    Row(
        modifier = Modifier
            .padding(2.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        TextField(
            value = messageText,
            onValueChange = { messageText = it },
            modifier = Modifier
                .background(Color.White)
                .weight(1f)
                .zIndex(1f),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Send,
                keyboardType = KeyboardType.Text
            ),
            singleLine = false,
            placeholder = { Text(text = "Mesajınızı yazın...") },
            trailingIcon = {
                if (isSending) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    IconButton(
                        onClick = {
                            if (messageText.isNotBlank()) {
                                isSending = true
                                onSend(messageText)
                                messageText = ""
                                coroutineScope.launch {
                                    delay(1000) // Simulating a delay for the message to be sent
                                    isSending = false
                                }
                            }
                        }
                    ) {
                        Icon(
                            imageVector = Icons.Filled.Send,
                            contentDescription = "Send",
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                }
            }
        )
    }
}

