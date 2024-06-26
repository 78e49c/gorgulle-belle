package com.example.gorgullebelle.app.presentation.screen

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gorgullebelle.app.presentation.components.CustomTopAppBar
import com.example.gorgullebelle.app.presentation.components.TopicSelectionDialog
import com.example.gorgullebelle.app.presentation.navigation.Route
import com.example.gorgullebelle.app.presentation.viewmodel.ProfileViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTopicScreen(
    navigate: (String) -> Unit = {},
    profileViewModel: ProfileViewModel = viewModel()
) {
    val topics by profileViewModel.topics.observeAsState(emptyList())
    var newTopic by remember { mutableStateOf("") }

    var expanded by remember { mutableStateOf(false) }
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CustomTopAppBar(
                conversationTitle = "Konuları Düzenle",
                onBackPressed = { navigate(Route.QuestionListScreen.route) },
                menuContent = {
                    IconButton(onClick = { expanded = !expanded }) {
                        Icon(
                            Icons.Default.MoreVert,
                            contentDescription = "Menu"
                        )
                    }
                    DropdownMenu(
                        expanded = expanded,
                        onDismissRequest = { expanded = false }
                    ) {
                        DropdownMenuItem(
                            text = { Text("Konu Seç") },
                            onClick = {
                                showDialog = true
                                expanded = false
                            }
                        )
                    }
                }
            )
        }
    ) { paddingValues ->

        if (showDialog) {
            TopicSelectionDialog(
                profileViewModel = profileViewModel,
                onDismissRequest = { showDialog = false },
                onTopicSelected = { selectedTopic ->

                    profileViewModel.setSelectedTopicIndex(selectedTopic)

                    showDialog = false
                }
            )
        }
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f)
            ) {
                items(topics) { topic ->
                    Column {
                        TopicItem(
                            topic = topic,
                            onRemove = { profileViewModel.removeTopic(it) }
                        )
                        Spacer(
                            modifier = Modifier
                                .height(1.dp)
                                .fillMaxWidth()
                                .background(Color.LightGray)
                        )
                    }
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                TextField(
                    value = newTopic,
                    onValueChange = { newTopic = it },
                    modifier = Modifier
                        .weight(1f)
                        .border(2.dp, MaterialTheme.colorScheme.onSurface, RoundedCornerShape(4.dp)),
                    placeholder = { Text("Yeni Konu") }
                )
                IconButton(
                    onClick = {
                        if (newTopic.isNotBlank()) {
                            profileViewModel.addTopic(newTopic)
                            newTopic = ""
                        }
                    },
                    modifier = Modifier.padding(start = 8.dp)
                ) {
                    Icon(Icons.Default.Add, contentDescription = "Add Topic")
                }
            }
        }

    }
}



@Composable
fun TopicItem(
    topic: String,
    onRemove: (String) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = topic,
            modifier = Modifier.weight(1f),
            style = MaterialTheme.typography.bodyLarge,

        )
        IconButton(onClick = { onRemove(topic) }) {
            Icon(Icons.Default.Delete, contentDescription = "Remove Topic")
        }
    }
}
