package com.example.gorgullebelle.app.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object PackageManager {

    private val _selectedPackageIndex = MutableStateFlow(0)
    val selectedPackageIndex: StateFlow<Int> = _selectedPackageIndex

    private val _canSendMessages = List(10) { MutableStateFlow(true) }
    val canSendMessages: List<StateFlow<Boolean>> = _canSendMessages

    // Seçili paket indeksini ayarlar.
    fun setSelectedPackageIndex(index: Int) {
        if (index in _canSendMessages.indices) {
            _selectedPackageIndex.value = index
        }
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

    // Mesaj göndermeyi devre dışı bırakır.
    fun disableSending(index: Int) {
        if (index in _canSendMessages.indices) {
            _canSendMessages[index].value = false
        }
    }
}
