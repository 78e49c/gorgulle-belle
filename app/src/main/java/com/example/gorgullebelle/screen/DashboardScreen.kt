package com.example.gorgullebelle.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.gorgullebelle.navigation.Route

@Composable
fun DashboardScreen(navigate: (String) -> Unit = {}) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Button(onClick = { navigate(Route.SignUpScreen.route) }) {
            Text(text = "giriş vs")
        }

        Button(onClick = { navigate(Route.ConversationListScreen.route) }) {
            Text(text = "konuşmalar")
        }

    }
}