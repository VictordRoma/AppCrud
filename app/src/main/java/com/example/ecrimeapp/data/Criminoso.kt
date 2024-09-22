package com.example.ecrimeapp.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "criminoso")
data class Criminoso(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val vulgo: String,
    val crime: String,
    val timeCoracao: String,
    val qtdPassagens: Int,
    val bairro: String,
    val iconId: Int
)
