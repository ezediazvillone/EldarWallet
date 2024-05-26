package com.example.eldarwallet.domain.usecase

import com.example.eldarwallet.domain.model.User
import javax.inject.Inject

class GetUserUseCase @Inject constructor() {

    operator fun invoke(): User? {
        return User(
            id = 1,
            name = "Ezequiel",
            lastName = "Diaz Villone"
        ) //todo: call repo, repo calls local data source and local data source call prefs
    }

}