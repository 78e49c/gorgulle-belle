package com.example.gorgullebelle.app

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.gorgullebelle.app.navigation.Route

@Composable
fun DashboardScreen(navigate: (String) -> Unit = {}) {

    Button(onClick = { navigate(Route.SignUpScreen.route)}) {
        Text(text = "kdskfskı")
    }
}