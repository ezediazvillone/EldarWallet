package com.example.eldarwallet.data.datasource.local

import com.example.eldarwallet.data.datasource.local.db.dao.CardDao
import com.example.eldarwallet.data.datasource.local.model.CardEntity
import com.example.eldarwallet.data.datasource.local.model.UserEntity
import com.example.eldarwallet.data.datasource.local.prefs.SharedPrefs
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val prefs: SharedPrefs,
    private val cardDao: CardDao
) : LocalDataSource {

    override fun getUser() = UserEntity(prefs.name, prefs.lastName)

    override fun saveUser(user: UserEntity) {
        prefs.name = user.name
        prefs.lastName = user.lastName
    }

    override fun getDebt() = prefs.debt.toDouble()

    override fun setDebt(debt: Double) {
        prefs.debt = debt.toString()
    }

    override suspend fun getCardList() = cardDao.getAllCards()

    override suspend fun getCardById(id: Int) = cardDao.getCardById(id)

    override suspend fun insertCard(card: CardEntity): Boolean {
        return try {
            cardDao.insertCard(card)
            true
        } catch (e: Exception) {
            false
        }
    }

}