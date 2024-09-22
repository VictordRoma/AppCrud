package com.example.ecrimeapp.data

import android.content.Context
import androidx.room.Room

class AppContainer(context: Context) {
    private val database: CrimonosoDatabase by lazy {
        Room.databaseBuilder(context, CrimonosoDatabase::class.java, "bd_crime").build()
    }

    val criminosoRepository: CriminosoRepository by lazy {
        CriminosoRepository(database.crimonosoDao())
    }
}