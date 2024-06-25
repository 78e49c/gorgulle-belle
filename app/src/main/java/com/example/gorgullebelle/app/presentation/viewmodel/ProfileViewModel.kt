package com.example.gorgullebelle.app.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ProfileViewModel : ViewModel() {
    private val _score = MutableLiveData(0)
    private val _topics = MutableLiveData<List<String>>(emptyList())
    private val _systemMessageVisible = MutableLiveData(false)

    val score: LiveData<Int> get() = _score
    val topics: LiveData<List<String>> get() = _topics
    val systemMessageVisible: LiveData<Boolean> get() = _systemMessageVisible

    fun updateScore(delta: Int) {
        _score.value = (_score.value ?: 0) + delta
    }

    fun addTopic(topic: String) {
        _topics.value = _topics.value?.toMutableList()?.apply { add(topic) }
    }

    fun removeTopic(topic: String) {
        _topics.value = _topics.value?.toMutableList()?.apply { remove(topic) }
    }

    fun setSystemMessageVisible(visible: Boolean) {
        _systemMessageVisible.value = visible
    }
}
