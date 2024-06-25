package com.example.gorgullebelle.app.presentation.navigation

sealed class Route(
    val route: String
) {
    object DashboardScreen : Route("dashboardScreen")
    object ProfileScreen : Route("profileScreen")
    object SignUpScreen : Route("signUpScreen")
    object SignInScreen : Route("signInScreen")
    object ConversationScreen : Route("conversationScreen")
    object ConversationListScreen : Route("conversationListScreen")
    object QuestionScreen : Route("questionScreen")
    object QuestionListScreen : Route("questionListScreen")
    object AddTopicScreen : Route("addTopicScreen")
}
