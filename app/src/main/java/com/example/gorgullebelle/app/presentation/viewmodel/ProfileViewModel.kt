package com.example.gorgullebelle.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    private val _score = MutableLiveData(0)
    private val _topics = MutableLiveData<List<String>>(emptyList())
    val score: LiveData<Int> get() = _score

    fun updateScore(delta: Int) {
        _score.value = (_score.value ?: 0) + delta
    }

    val topics: LiveData<List<String>> get() = _topics

    fun addTopic(topic: String) {
        _topics.value = _topics.value?.toMutableList()?.apply { add(topic) }
    }

    fun removeTopic(topic: String) {
        _topics.value = _topics.value?.toMutableList()?.apply { remove(topic) }
    }
}


