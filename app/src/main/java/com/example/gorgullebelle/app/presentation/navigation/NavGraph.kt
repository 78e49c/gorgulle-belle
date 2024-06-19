package com.example.gorgullebelle.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gorgullebelle.app.presentation.screen.ConversationListScreen
import com.example.gorgullebelle.app.presentation.screen.DashboardScreen
import com.example.gorgullebelle.app.presentation.screen.ExerciseListScreen
import com.example.gorgullebelle.app.presentation.screen.ExerciseScreen
import com.example.gorgullebelle.app.presentation.screen.ExperienceScreen
import com.example.gorgullebelle.app.presentation.screen.ProfileScreen
import com.example.gorgullebelle.app.presentation.screen.SignInScreen
import com.example.gorgullebelle.app.presentation.screen.SignUpScreen
import com.example.gorgullebelle.app.presentation.viewmodel.ChatManagerViewModel

@Composable
fun NavGraph(chatManagerViewModel: ChatManagerViewModel) {
    val navController = rememberNavController()

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
                chatManagerViewModel = chatManagerViewModel,
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(Route.ConversationListScreen.route) {
            ConversationListScreen(
                navigate = { route ->
                    navController.navigate(route)
                },
                chatManagerViewModel = chatManagerViewModel
            )
        }

        composable(Route.ProfileScreen.route) {
            ProfileScreen(
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(Route.ExerciseScreen.route) {
            ExerciseScreen(
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(Route.ExerciseListScreen.route) {
            ExerciseListScreen(
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }
    }
}
