package com.example.gorgullebelle.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gorgullebelle.app.presentation.navigation.NavGraph

import com.example.gorgullebelle.app.presentation.viewmodel.ChatManagerViewModel
import com.example.gorgullebelle.app.presentation.viewmodel.ProfileViewModel
import com.example.gorgullebelle.app.presentation.viewmodel.QuestionViewModel
import com.example.gorgullebelle.app.presentation.viewmodel.UserViewModel
import com.example.gorgullebelle.ui.theme.GBTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GBTheme {
                val chatManagerViewModel: ChatManagerViewModel = viewModel()
                val questionViewModel: QuestionViewModel = viewModel()
                val profileViewModel: ProfileViewModel = viewModel()
                val userViewModel: UserViewModel = viewModel()
                NavGraph(
                    chatManagerViewModel = chatManagerViewModel,
                    questionViewModel = questionViewModel,
                    profileViewModel = profileViewModel,
                    userViewModel = userViewModel
                )
            }

        }
    }
}
