package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.data.System
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.ChatManagerViewModel
import kotlinx.coroutines.flow.StateFlow


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationListScreen(navigate: (String) -> Unit = {}) {
    val chatSessionManagerViewModel: ChatManagerViewModel = viewModel()
    val messagePackages = System.messagePackages

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                title = "Konuşma",
                onIconClick = { navigate(Route.DashboardScreen.route) }
            )
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Column {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(8.dp)
                ) {
                    item {
                        ConversationListItem(
                            title = "AI Dedektifi",
                            imageResId = R.drawable.ai_dedektifi,
                            messagesFlow = messagePackages.getOrNull(0),
                            onClick = {
                                System.setSelectedPackageIndex(0)
                                System.disableSending(0)
                                chatSessionManagerViewModel.updateCurrentSessionId(0)
                                navigate(Route.ExperienceScreen.route)
                            }
                        )
                    }

                    item {
                        ConversationListItem(
                            title = "Gönül Hasbihali",
                            imageResId = R.drawable.gonul_hasbihali,
                            messagesFlow = messagePackages.getOrNull(1),
                            onClick = {
                                System.setSelectedPackageIndex(1)
                                System.disableSending(1)
                                chatSessionManagerViewModel.updateCurrentSessionId(1)
                                navigate(Route.ExperienceScreen.route)
                            }
                        )
                    }

                    item {
                        ConversationListItem(
                            title = "Şam Yolculuğu",
                            imageResId = R.drawable.sam_yolculugu,
                            messagesFlow = messagePackages.getOrNull(2),
                            onClick = {
                                System.setSelectedPackageIndex(2)
                                System.disableSending(2)
                                chatSessionManagerViewModel.updateCurrentSessionId(2)
                                navigate(Route.ExperienceScreen.route)
                            }
                        )
                    }
                }
            }
        }
    }
}
@Composable
fun ConversationListItem(title: String, imageResId: Int, messagesFlow: StateFlow<List<String>>?, onClick: () -> Unit) {
    val messages by messagesFlow?.collectAsState() ?: remember { mutableStateOf(emptyList()) }
    val lastMessage = messages.lastOrNull() ?: "No messages yet"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onClick() }
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
            Text(text = lastMessage, style = MaterialTheme.typography.bodyMedium)
        }
    }
}
