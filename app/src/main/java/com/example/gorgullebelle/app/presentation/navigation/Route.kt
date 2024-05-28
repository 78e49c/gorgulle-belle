package com.example.gorgullebelle.app.presentation.navigation

sealed class Route(
    val route: String
) {
    object SignUpScreen : Route("signUpScreen")
    object DashboardScreen : Route("dashboardScreen")
    object SignInScreen : Route("signInScreen")
    object ExperienceScreen : Route("experienceScreen")
    object ConversationListScreen : Route("conversationListScreen")
    object ProfileScreen : Route("profileScreen")
    object ExerciseScreen : Route("exerciseScreen")
    object ExerciseListScreen : Route("exerciseListScreen")
}
