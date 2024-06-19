package com.example.gorgullebelle.app.presentation.screen

import android.util.Log
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.ChatManagerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationListScreen(navigate: (String) -> Unit = {}, chatManagerViewModel: ChatManagerViewModel) {
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
                            sessionId = 0,
                            chatManagerViewModel = chatManagerViewModel,
                            navigate = navigate
                        )
                    }
                    item {
                        ConversationListItem(
                            title = "Gönül Hasbihali",
                            imageResId = R.drawable.gonul_hasbihali,
                            sessionId = 1,
                            chatManagerViewModel = chatManagerViewModel,
                            navigate = navigate
                        )
                    }
                    item {
                        ConversationListItem(
                            title = "Şam Yolculuğu",
                            imageResId = R.drawable.sam_yolculugu,
                            sessionId = 2,
                            chatManagerViewModel = chatManagerViewModel,
                            navigate = navigate
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun ConversationListItem(
    title: String,
    imageResId: Int,
    sessionId: Int,
    chatManagerViewModel: ChatManagerViewModel,
    navigate: (String) -> Unit
) {
    val messagesFlow = chatManagerViewModel.getSessionMessages(sessionId).collectAsState()

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
            Text(text = messagesFlow.value.lastOrNull() ?: "No messages yet", style = MaterialTheme.typography.bodyMedium)
        }
    }
}
