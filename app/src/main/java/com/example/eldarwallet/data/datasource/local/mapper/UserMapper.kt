package com.example.eldarwallet.data.datasource.local.mapper

import com.example.eldarwallet.data.datasource.local.model.UserEntity
import com.example.eldarwallet.domain.model.User
import javax.inject.Inject

class UserMapper @Inject constructor() {

    fun toEntity(user: User): UserEntity {
        return UserEntity(
            name = user.name,
            lastName = user.lastName
        )
    }

    fun fromEntity(userEntity: UserEntity): User {
        return User(
            name = userEntity.name,
            lastName = userEntity.lastName
        )
    }

}