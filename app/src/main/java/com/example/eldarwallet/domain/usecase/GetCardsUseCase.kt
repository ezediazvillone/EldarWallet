package com.example.eldarwallet.domain.usecase

import com.example.eldarwallet.domain.model.Card
import javax.inject.Inject

class GetCardsUseCase @Inject constructor() {

    operator fun invoke() = listOf(
        Card(
            id = 1,
            type = "Mastercard",
            number = "5031 7557 3453 0604",
            securityCode = 123,
            dueDate = "11/25"
        ), Card(
            id = 2,
            type = "Visa",
            number = "4509 9535 6623 3704",
            securityCode = 123,
            dueDate = "11/25"
        ), Card(
            id = 3,
            type = "American Express",
            number = "3711 803032 57522",
            securityCode = 1234,
            dueDate = "11/25"
        )
    ) //todo: call repo, repo calls local data source and local data source call db

}