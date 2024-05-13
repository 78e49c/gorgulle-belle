package com.example.gorgullebelle.app.navigation

sealed class Route(
    val route: String
) {
    object SignUpScreen : Route("signUpScreen")
    object DashboardScreen : Route("dashboardScreen")

}