package com.example.eldarwallet.domain.usecase

import com.example.eldarwallet.data.repository.RepositoryImpl
import javax.inject.Inject

class GetCardByIdUseCase @Inject constructor(
    private val repo: RepositoryImpl
) {

    suspend operator fun invoke(id: Int) = repo.getCardById(id)

}