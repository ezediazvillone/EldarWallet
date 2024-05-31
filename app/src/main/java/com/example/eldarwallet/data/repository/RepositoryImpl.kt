package com.example.eldarwallet.data.repository

import com.example.eldarwallet.data.datasource.local.LocalDataSourceImpl
import com.example.eldarwallet.data.datasource.local.mapper.UserMapper
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSourceImpl,
    private val userMapper: UserMapper
) : Repository {

    override fun getUser() = userMapper.fromEntity(localDataSource.getUser())

    override fun saveUser(user: User) = localDataSource.saveUser(userMapper.toEntity(user))

    override fun getDebt() = localDataSource.getDebt()

    override fun setDebt(debt: Double) = localDataSource.setDebt(debt)

}