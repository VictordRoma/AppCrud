package com.example.ecrimeapp.data

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Criminoso::class], version = 1, exportSchema = false)
abstract class CrimonosoDatabase : RoomDatabase() {
    abstract fun crimonosoDao(): CrimonosoDao
}
