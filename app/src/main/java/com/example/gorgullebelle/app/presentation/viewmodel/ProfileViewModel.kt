package com.example.gorgullebelle.app.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.gorgullebelle.app.data.LocalStorageHelper
import com.example.gorgullebelle.app.data.questionTopic

class ProfileViewModel(application: Application) : AndroidViewModel(application) {
    private val _score = MutableLiveData(LocalStorageHelper.loadScore(application))
    private val _topics = MutableLiveData(LocalStorageHelper.loadTopics(application))
    private val _systemMessageVisible = MutableLiveData(false)
    private val _selectedTopicIndex = MutableLiveData(0)

    val score: LiveData<Int> get() = _score
    val topics: LiveData<List<String>> get() = _topics
    val systemMessageVisible: LiveData<Boolean> get() = _systemMessageVisible
    val selectedTopic: LiveData<String> get() = MutableLiveData(_topics.value?.getOrNull(_selectedTopicIndex.value ?: 0) ?: "")

    init {
        Log.d("ProfileViewModel", "Initializing ProfileViewModel")
        loadScoreAndTopics()
    }

    fun setSystemMessageVisible(visible: Boolean) {
        _systemMessageVisible.value = visible
    }

    fun updateScore(delta: Int) {
        _score.value = (_score.value ?: 0) + delta
        Log.d("ProfileViewModel", "Updated score: ${_score.value}")
        saveScore()
    }

    fun addTopic(topic: String) {
        _topics.value = _topics.value?.toMutableList()?.apply { add(topic) }
        Log.d("ProfileViewModel", "Added topic: $topic")
        saveTopics()
        if (_selectedTopicIndex.value == null) {
            _selectedTopicIndex.value = 0
        }
    }

    fun removeTopic(topic: String) {
        _topics.value = _topics.value?.toMutableList()?.apply { remove(topic) }
        Log.d("ProfileViewModel", "Removed topic: $topic")
        saveTopics()
        if (_selectedTopicIndex.value != null && _selectedTopicIndex.value!! >= _topics.value!!.size) {
            _selectedTopicIndex.value = _topics.value!!.size - 1
        }
    }

    fun setSelectedTopicIndex(index: Int) {
        val topicsList = _topics.value
        if (index in 0 until (topicsList?.size ?: 0)) {
            _selectedTopicIndex.value = index
            questionTopic = topicsList?.get(index) ?: ""
            Log.d("ProfileViewModel", "Selected topic index: $index, topic: ${questionTopic}")
        }
    }

    private fun loadScoreAndTopics() {
        Log.d("ProfileViewModel", "Loading score and topics")
        _score.value = LocalStorageHelper.loadScore(getApplication())
        _topics.value = LocalStorageHelper.loadTopics(getApplication())
    }

    private fun saveScore() {
        _score.value?.let {
            Log.d("ProfileViewModel", "Saving score: $it")
            LocalStorageHelper.saveScore(getApplication(), it)
        }
    }

    private fun saveTopics() {
        _topics.value?.let {
            Log.d("ProfileViewModel", "Saving topics: $it")
            LocalStorageHelper.saveTopics(getApplication(), it)
        }
    }


}
