package com.example.gorgullebelle.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    private val _score = MutableLiveData(0)
    val score: LiveData<Int> get() = _score

    fun updateScore(delta: Int) {
        _score.value = (_score.value ?: 0) + delta
    }
}
