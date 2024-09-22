package com.example.ecrimeapp.data

import kotlinx.coroutines.flow.Flow

open class CriminosoRepository(private val crimonosoDao: CrimonosoDao) {
    fun getAllBandidos(): Flow<List<Criminoso>> = crimonosoDao.getAllBandidos()

    fun getBandidoById(id: Int): Flow<Criminoso> = crimonosoDao.getBandidoById(id)

    suspend fun insertBandido(criminoso: Criminoso) = crimonosoDao.insertBandido(criminoso)

    suspend fun deleteBandido(criminoso: Criminoso) = crimonosoDao.deleteBandido(criminoso)
}
