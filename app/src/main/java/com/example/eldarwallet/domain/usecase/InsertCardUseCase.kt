package com.example.eldarwallet.domain.usecase

import com.example.eldarwallet.data.repository.RepositoryImpl
import com.example.eldarwallet.domain.model.Card
import javax.inject.Inject

class InsertCardUseCase @Inject constructor(
    private val repo: RepositoryImpl
) {

    suspend operator fun invoke(card: Card): Boolean = repo.insertCard(card)

}