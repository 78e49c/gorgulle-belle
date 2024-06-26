package com.example.gorgullebelle.app.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gorgullebelle.app.presentation.screen.AddTopicScreen
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
import com.example.gorgullebelle.app.presentation.viewmodel.UserViewModel

@Composable
fun NavGraph(
    chatManagerViewModel: ChatManagerViewModel,
    questionViewModel: QuestionViewModel,
    profileViewModel: ProfileViewModel,
    userViewModel: UserViewModel
) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Route.DashboardScreen.route
    ) {
        composable(Route.SignUpScreen.route) {
            SignUpScreen(
                userViewModel = userViewModel,
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(Route.DashboardScreen.route) {
            DashboardScreen(
                chatManagerViewModel = chatManagerViewModel,
                questionViewModel = questionViewModel,
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(Route.SignInScreen.route) {
            SignInScreen(
                userViewModel = userViewModel,
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(Route.ConversationScreen.route) {
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
                profileViewModel = profileViewModel,
                chatManagerViewModel = chatManagerViewModel,
                navigate = { route ->
                    navController.navigate(route)
                }

            )
        }

        composable(Route.ProfileScreen.route) {
            ProfileScreen(
                profileViewModel = profileViewModel,  // ProfileViewModel'i buradan geçiyoruz
                userViewModel = userViewModel,
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }

        composable(Route.QuestionScreen.route) {
            QuestionScreen(
                questionViewModel = questionViewModel,
                profileViewModel = profileViewModel,
                navigate = { route ->
                    navController.navigate(route)
                },
            )
        }

        composable(Route.QuestionListScreen.route) {
            QuestionListScreen(
                questionViewModel = questionViewModel,
                profileViewModel = profileViewModel,
                navigate = { route ->
                    navController.navigate(route)
                },
            )
        }

        composable(Route.AddTopicScreen.route) {
            AddTopicScreen(
                profileViewModel = profileViewModel,
                navigate = { route ->
                    navController.navigate(route)
                }
            )
        }


    }
}
