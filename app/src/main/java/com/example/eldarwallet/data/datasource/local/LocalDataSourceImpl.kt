package com.example.eldarwallet.data.datasource.local

import com.example.eldarwallet.data.datasource.local.model.UserEntity
import com.example.eldarwallet.data.datasource.local.prefs.SharedPrefs
import javax.inject.Inject

class LocalDataSourceImpl @Inject constructor(
    private val prefs: SharedPrefs
) : LocalDataSource {

    override fun getUser() = UserEntity(prefs.name, prefs.lastName)

    override fun saveUser(user: UserEntity) {
        prefs.name = user.name
        prefs.lastName = user.lastName
    }

}