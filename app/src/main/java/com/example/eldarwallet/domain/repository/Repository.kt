package com.example.eldarwallet.domain.repository

import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.model.User

interface Repository {

    fun getUser(): User

    fun saveUser(user: User)

    fun getDebt(): Double

    fun setDebt(debt: Double)

    suspend fun getCardList(): List<Card>

    suspend fun getCardById(id: Int): Card

    suspend fun insertCard(card: Card): Boolean

}