package com.example.eldarwallet.domain.usecase

import com.example.eldarwallet.data.repository.RepositoryImpl
import com.example.eldarwallet.domain.model.User
import javax.inject.Inject

class SaveUserUseCase @Inject constructor(
    private val repo: RepositoryImpl
) {

    operator fun invoke(user: User) = repo.saveUser(user)

}