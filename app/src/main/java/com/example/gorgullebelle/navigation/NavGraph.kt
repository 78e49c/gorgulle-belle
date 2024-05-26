package com.example.gorgullebelle.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gorgullebelle.app.MessageViewModel
import com.example.gorgullebelle.screen.ConversationListScreen
import com.example.gorgullebelle.screen.DashboardScreen
import com.example.gorgullebelle.screen.SignInScreen
import com.example.gorgullebelle.screen.SignUpScreen
import com.example.gorgullebelle.screen.ExperienceScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()
    val messageViewModel: MessageViewModel = viewModel()

    NavHost(
        navController = navController,
        startDestination = Route.DashboardScreen.route
    ) {
        composable(Route.SignUpScreen.route) {
            SignUpScreen(
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(Route.DashboardScreen.route) {
            DashboardScreen(
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(Route.SignInScreen.route) {
            SignInScreen(
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(Route.ExperienceScreen.route) {
            ExperienceScreen(
                messageViewModel = messageViewModel,
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(Route.ConversationListScreen.route) {
            ConversationListScreen(
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }
    }
}
