package com.example.gorgullebelle.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gorgullebelle.navigation.Route
import com.example.gorgullebelle.app.MessageViewModel
import com.example.gorgullebelle.app.System

@Composable
fun ConversationListScreen(navigate: (String) -> Unit = {}) {
    val messageViewModel: MessageViewModel = viewModel()
    val messagePackages = System.messagePackages

    Button(onClick = { navigate(Route.DashboardScreen.route) }) {
        Text(text = "MENÜ")
    }

    LazyColumn(
        modifier = Modifier.fillMaxSize().padding(8.dp)
    ) {
        itemsIndexed(messagePackages) { index, messagesFlow ->
            val messages by messagesFlow.collectAsState()

            ConversationListItem(
                title = "Conversation $index",
                lastMessage = messages.lastOrNull() ?: "No messages yet",
                onClick = {
                    System.setSelectedPackageIndex(index)
                    messageViewModel.updateCurrentSessionId(index)
                    navigate(Route.ExperienceScreen.route)
                }
            )
        }
    }
}

@Composable
fun ConversationListItem(title: String, lastMessage: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = title)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = lastMessage)
        }
    }
}
