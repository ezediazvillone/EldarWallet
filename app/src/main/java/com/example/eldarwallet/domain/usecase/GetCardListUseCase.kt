package com.example.eldarwallet.domain.usecase

import com.example.eldarwallet.data.repository.RepositoryImpl
import javax.inject.Inject

class GetCardListUseCase @Inject constructor(
    private val repo: RepositoryImpl
) {

    suspend operator fun invoke() = repo.getCardList()

}