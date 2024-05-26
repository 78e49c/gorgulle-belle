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

    // Belirtilen paket indeksine mesaj ekler.
    fun addMessage(packageIndex: Int, message: String) {
        if (packageIndex in _messagePackages.indices) {
            _messagePackages[packageIndex].value = _messagePackages[packageIndex].value + message
        }
    }

    // Seçili paket indeksini ayarlar.
    fun setSelectedPackageIndex(index: Int) {
        if (index in _messagePackages.indices) {
            _selectedPackageIndex.value = index
        }
    }

    // Seçili paketteki mesajları getirir.
    fun getSelectedMessages(): List<String> {
        return _messagePackages[_selectedPackageIndex.value].value
    }

    // Belirtilen indeks için mesaj gönderme durumunu getirir.
    fun getCanSendMessages(index: Int): StateFlow<Boolean> {
        return _canSendMessages[index]
    }

    // Belirtilen indeks için mesaj gönderme durumunu ayarlar.
    fun setCanSendMessages(index: Int, canSend: Boolean) {
        if (index in _canSendMessages.indices) {
            _canSendMessages[index].value = canSend
        }
    }

    // Mesajları sıfırlar ve gönderme durumunu etkinleştirir.
    fun resetMessages(index: Int, messageViewModel: MessageViewModel) {
        if (index in _messagePackages.indices) {
            _messagePackages[index].value = emptyList()
            setCanSendMessages(index, true)
            messageViewModel.clearMessages(index)
        }
    }

    // Mesaj göndermeyi devre dışı bırakır.
    fun disableSending(index: Int) {
        if (index in _canSendMessages.indices) {
            _canSendMessages[index].value = false
        }
    }
}
