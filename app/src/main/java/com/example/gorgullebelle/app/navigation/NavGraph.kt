package com.example.gorgullebelle.app.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gorgullebelle.app.DashboardScreen
import com.example.gorgullebelle.app.SignUpScreen

@Composable
fun NavGraph() {
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
    }
}