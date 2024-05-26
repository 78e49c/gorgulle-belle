package com.example.gorgullebelle.app

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

object System {

    private val _selectedPackageIndex = MutableStateFlow(0)
    val selectedPackageIndex: StateFlow<Int> = _selectedPackageIndex

    private val _messagePackages = List(10) { MutableStateFlow<List<String>>(emptyList()) }
    val messagePackages: List<StateFlow<List<String>>> = _messagePackages

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
}
