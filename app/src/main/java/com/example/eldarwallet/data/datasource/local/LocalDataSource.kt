package com.example.eldarwallet.data.datasource.local

import com.example.eldarwallet.data.datasource.local.model.UserEntity

interface LocalDataSource {

    fun getUser(): UserEntity

    fun saveUser(user: UserEntity)

}