package com.example.gorgullebelle.app

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class System : ViewModel() {
    private val messagePackages = List(10) { mutableStateListOf<String>() }

    // Selected package index as a mutable state
    private val _selectedPackageIndex = MutableStateFlow(0)
    val selectedPackageIndex: StateFlow<Int> = _selectedPackageIndex

    val messages: List<String>
        get() = messagePackages[_selectedPackageIndex.value]

    fun addMessage(message: String) {
        messagePackages[_selectedPackageIndex.value].add(message)
    }

    fun selectPackage(index: Int) {
        if (index in messagePackages.indices) {
            _selectedPackageIndex.value = index
        }
    }
}
