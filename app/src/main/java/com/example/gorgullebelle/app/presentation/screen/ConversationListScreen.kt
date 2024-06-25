package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gorgullebelle.R
import com.example.gorgullebelle.app.presentation.components.ConversationListItem
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.ChatManagerViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ConversationListScreen(
    navigate: (String) -> Unit = {},
    chatManagerViewModel: ChatManagerViewModel
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                conversationTitle  = "Konuşma",
                onBackPressed = { navigate(Route.DashboardScreen.route) }
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
                    val onItemClick: (Int) -> Unit = { sessionId ->
                        chatManagerViewModel.setSelectedPackageIndex(sessionId)
                        navigate(Route.ConversationScreen.route)
                    }

                    item {
                        ConversationListItem(
                            title = "AI Dedektifi",
                            imageResId = R.drawable.ai_dedektifi,
                            sessionId = 0,
                            chatManagerViewModel = chatManagerViewModel,
                            onItemClick = onItemClick
                        )
                    }
                    item {
                        ConversationListItem(
                            title = "Gönül Hasbihali",
                            imageResId = R.drawable.gonul_hasbihali,
                            sessionId = 1,
                            chatManagerViewModel = chatManagerViewModel,
                            onItemClick = onItemClick
                        )
                    }
                    item {
                        ConversationListItem(
                            title = "Şam Yolculuğu",
                            imageResId = R.drawable.sam_yolculugu,
                            sessionId = 2,
                            chatManagerViewModel = chatManagerViewModel,
                            onItemClick = onItemClick
                        )
                    }
                }
            }
        }
    }
}

