package com.example.eldarwallet.domain.usecase

import com.example.eldarwallet.data.repository.RepositoryImpl
import javax.inject.Inject

class GetDebtUseCase @Inject constructor(
    private val repo: RepositoryImpl
) {

    operator fun invoke() = repo.getDebt()

}