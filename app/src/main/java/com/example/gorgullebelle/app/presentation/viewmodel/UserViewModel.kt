package com.example.gorgullebelle.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

data class User(
    val name: String,
    val surname: String,
    val email: String,
    val password: String,
    var score: Int = 0
)

class UserViewModel : ViewModel() {

    private val users = mutableListOf(
        User("Giriş", "Yap", "puanlarini@kaydetmek.icin", "11111111")
    )

    private val _onlineUser = MutableStateFlow(users[0])
    val onlineUser: StateFlow<User> get() = _onlineUser

    fun signUp(name: String, surname: String, email: String, password: String) {
        viewModelScope.launch {
            val user = User(name, surname, email, password)
            users.add(user)
        }
    }

    fun signIn(email: String, password: String): Pair<String, String>? {
        val userIndex = users.indexOfFirst { it.email == email && it.password == password }
        return if (userIndex != -1 && _onlineUser.value == users[0]) {
            _onlineUser.value = users[userIndex]
            Pair(users[userIndex].name, users[userIndex].surname)
        } else {
            null
        }
    }

    fun signOut() {
        _onlineUser.value = users[0]
    }

    fun updateScore(scoreChange: Int) {
        val currentOnlineUser = _onlineUser.value
        //if (currentOnlineUser != users[0]) {
            currentOnlineUser.score += scoreChange
            _onlineUser.value = currentOnlineUser
        //}
    }
}
