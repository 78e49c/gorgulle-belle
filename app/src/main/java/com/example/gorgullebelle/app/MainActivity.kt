package com.example.gorgullebelle.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gorgullebelle.app.presentation.navigation.NavGraph
import com.example.gorgullebelle.app.presentation.viewmodel.ChatManagerViewModel
import com.example.gorgullebelle.ui.theme.GBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GBTheme {
                val chatManagerViewModel: ChatManagerViewModel = viewModel()
                NavGraph(chatManagerViewModel = chatManagerViewModel)
            }
        }
    }
}


