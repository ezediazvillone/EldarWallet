package com.example.eldarwallet.data.datasource.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.eldarwallet.data.datasource.local.db.dao.CardDao
import com.example.eldarwallet.data.datasource.local.model.CardEntity

@Database(entities = [CardEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getCardDao(): CardDao

}