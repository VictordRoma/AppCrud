package com.example.ecrimeapp.data

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface CrimonosoDao {
    @Query("SELECT * FROM criminoso")
    fun getAllBandidos(): Flow<List<Criminoso>>

    @Query("SELECT * FROM criminoso WHERE id = :id")
    fun getBandidoById(id: Int): Flow<Criminoso>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBandido(criminoso: Criminoso)

    @Delete
    suspend fun deleteBandido(criminoso: Criminoso)
}
