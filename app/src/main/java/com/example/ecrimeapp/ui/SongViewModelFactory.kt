package com.example.ecrimeapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.ecrimeapp.data.CriminosoRepository

class SongViewModelFactory(private val criminosoRepository: CriminosoRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CrimeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return CrimeViewModel(criminosoRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
