package com.example.eldarwallet.data.datasource.local

import com.example.eldarwallet.data.datasource.local.model.CardEntity
import com.example.eldarwallet.data.datasource.local.model.UserEntity

interface LocalDataSource {

    fun getUser(): UserEntity

    fun saveUser(user: UserEntity)

    fun getDebt(): Double

    fun setDebt(debt: Double)

    suspend fun getCardList(): List<CardEntity>

    suspend fun getCardById(id: Int): CardEntity

    suspend fun insertCard(card: CardEntity): Boolean

}