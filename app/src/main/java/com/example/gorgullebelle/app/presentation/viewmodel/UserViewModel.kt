package com.example.gorgullebelle.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {

    val names = mutableListOf<String>()
    val surnames = mutableListOf<String>()
    val emails = mutableListOf<String>()
    val passwords = mutableListOf<String>()

    fun signUp(name: String, surname: String, email: String, password: String) {
        viewModelScope.launch {
            names.add(name)
            surnames.add(surname)
            emails.add(email)
            passwords.add(password)
        }
    }

    fun signIn(email: String, password: String): Pair<String, String>? {
        val index = emails.indexOf(email)
        return if (index != -1 && passwords[index] == password) {
            Pair(names[index], surnames[index])
        } else {
            null
        }
    }
}
