package com.example.gorgullebelle.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gorgullebelle.app.presentation.screen.ConversationListScreen
import com.example.gorgullebelle.app.presentation.screen.ConversationScreen
import com.example.gorgullebelle.app.presentation.screen.DashboardScreen
import com.example.gorgullebelle.app.presentation.screen.ProfileScreen
import com.example.gorgullebelle.app.presentation.screen.QuestionListScreen
import com.example.gorgullebelle.app.presentation.screen.QuestionScreen
import com.example.gorgullebelle.app.presentation.screen.SignInScreen
import com.example.gorgullebelle.app.presentation.screen.SignUpScreen
import com.example.gorgullebelle.app.presentation.viewmodel.ChatManagerViewModel
import com.example.gorgullebelle.app.presentation.viewmodel.ProfileViewModel
import com.example.gorgullebelle.app.presentation.viewmodel.QuestionViewModel

@Composable
fun NavGraph(
    chatManagerViewModel: ChatManagerViewModel,
    questionViewModel: QuestionViewModel,
    profileViewModel: ProfileViewModel
) {
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
            ConversationScreen(
                chatManagerViewModel = chatManagerViewModel,
                profileViewModel = profileViewModel,
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
                },
                profileViewModel = profileViewModel  // ProfileViewModel'i buradan geçiyoruz
            )
        }

        composable(Route.ExerciseScreen.route) {
            QuestionScreen(
                navigate = { route ->
                    navController.navigate(route)
                },
                questionViewModel = questionViewModel,
                profileViewModel = profileViewModel
            )
        }

        composable(Route.ExerciseListScreen.route) {
            QuestionListScreen(
                navigate = { route ->
                    navController.navigate(route)
                },
                questionViewModel = questionViewModel
            )
        }
    }
}
