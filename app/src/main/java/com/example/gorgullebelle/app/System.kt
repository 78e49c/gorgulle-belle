package com.example.gorgullebelle.app

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object System {

    private val _selectedPackageIndex = MutableStateFlow(0)
    val selectedPackageIndex: StateFlow<Int> = _selectedPackageIndex

    private val _messagePackages = List(10) { MutableStateFlow<List<String>>(emptyList()) }
    val messagePackages: List<StateFlow<List<String>>> = _messagePackages

    private val _canSendMessages = List(10) { MutableStateFlow(true) }
    val canSendMessages: List<StateFlow<Boolean>> = _canSendMessages

    fun addMessage(packageIndex: Int, message: String) {
        if (packageIndex in _messagePackages.indices) {
            _messagePackages[packageIndex].value = _messagePackages[packageIndex].value + message
        }
    }

    fun setSelectedPackageIndex(index: Int) {
        if (index in _messagePackages.indices) {
            _selectedPackageIndex.value = index
        }
    }

    fun getSelectedMessages(): List<String> {
        return _messagePackages[_selectedPackageIndex.value].value
    }

    fun getCanSendMessages(index: Int): StateFlow<Boolean> {
        return _canSendMessages[index]
    }

    fun setCanSendMessages(index: Int, canSend: Boolean) {
        if (index in _canSendMessages.indices) {
            _canSendMessages[index].value = canSend
        }
    }

    fun resetMessages(index: Int, messageViewModel: MessageViewModel) {
        if (index in _messagePackages.indices) {
            _messagePackages[index].value = emptyList()
            setCanSendMessages(index, true)
            messageViewModel.clearMessages(index) // Mesajları gerçekten sil
        }
    }

    fun disableSending(index: Int) {
        if (index in _canSendMessages.indices) {
            _canSendMessages[index].value = false
        }
    }
}
