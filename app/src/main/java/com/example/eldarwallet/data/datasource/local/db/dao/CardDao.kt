package com.example.eldarwallet.data.datasource.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.eldarwallet.data.datasource.local.model.CardEntity

@Dao
interface CardDao {

    @Query("SELECT * FROM card_table")
    suspend fun getAllCards(): List<CardEntity>

    @Query("SELECT * FROM card_table WHERE id = :id")
    suspend fun getCardById(id: Int): CardEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCard(card: CardEntity)

}