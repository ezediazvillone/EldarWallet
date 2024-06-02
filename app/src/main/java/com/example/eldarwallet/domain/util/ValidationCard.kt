package com.example.eldarwallet.domain.util

import com.example.eldarwallet.domain.model.Card

sealed class ValidationCard {

    data class ErrorOnCardNumber(val errorMsg: String = "Número de tarjeta inválido") :
        ValidationCard()

    data class ErrorOnCardDueDate(val errorMsg: String = "Fecha de vencimiento inválida") :
        ValidationCard()

    data class ErrorOnCardSecurityCode(val errorMsg: String = "Código de seguridad inválido") :
        ValidationCard()

    data class CardValid(val card: Card) : ValidationCard()

    data object CardAlreadyRegistered : ValidationCard()

}