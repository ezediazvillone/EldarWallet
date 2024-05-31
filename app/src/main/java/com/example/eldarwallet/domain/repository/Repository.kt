package com.example.eldarwallet.domain.repository

import com.example.eldarwallet.domain.model.User

interface Repository {

    fun getUser(): User

    fun saveUser(user: User)

    fun getDebt(): Double

    fun setDebt(debt: Double)

}