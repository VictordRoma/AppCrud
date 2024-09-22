package com.example.ecrimeapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ecrimeapp.data.CriminosoRepository
import com.example.ecrimeapp.data.Criminoso
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch

class CrimeViewModel(private val repository: CriminosoRepository) : ViewModel() {

    val criminosoList: Flow<List<Criminoso>> = repository.getAllBandidos()

    fun getCriminosoById(id: Int): Flow<Criminoso> = repository.getBandidoById(id)

    fun addOrUpdateCriminoso(id: Int? = null, vulgo: String, qtdPassagens: Int, crime: String, timeCoracao: String, bairro: String, iconId: Int) {
        val criminoso = Criminoso(id = id ?: 0, vulgo = vulgo,  qtdPassagens = qtdPassagens, crime = crime, timeCoracao = timeCoracao, bairro = bairro, iconId = iconId)
        viewModelScope.launch {
            repository.insertBandido(criminoso)
        }
    }

    fun deleteBandido(criminoso: Criminoso) {
        viewModelScope.launch {
            repository.deleteBandido(criminoso)
        }
    }
}
