package com.example.eldarwallet.data.repository

import com.example.eldarwallet.data.datasource.local.LocalDataSourceImpl
import com.example.eldarwallet.data.datasource.local.mapper.CardMapper
import com.example.eldarwallet.data.datasource.local.mapper.UserMapper
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSourceImpl,
    private val userMapper: UserMapper,
    private val cardMapper: CardMapper
) : Repository {

    override fun getUser() = userMapper.fromEntity(localDataSource.getUser())

    override fun saveUser(user: User) = localDataSource.saveUser(userMapper.toEntity(user))

    override fun getDebt() = localDataSource.getDebt()

    override fun setDebt(debt: Double) = localDataSource.setDebt(debt)

    override suspend fun getCardList() =
        localDataSource.getCardList().map { cardMapper.fromEntity(it) }

    override suspend fun getCardById(id: Int) =
        cardMapper.fromEntity(localDataSource.getCardById(id))

    override suspend fun insertCard(card: Card): Boolean =
        localDataSource.insertCard(cardMapper.toEntity(card))

}