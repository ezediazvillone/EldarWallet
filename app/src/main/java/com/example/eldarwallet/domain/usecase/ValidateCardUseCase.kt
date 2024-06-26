package com.example.eldarwallet.domain.usecase

import com.example.eldarwallet.data.repository.RepositoryImpl
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.util.CardType.AMERICAN_EXPRESS
import com.example.eldarwallet.domain.util.CardType.MASTERCARD
import com.example.eldarwallet.domain.util.CardType.VISA
import com.example.eldarwallet.domain.util.ValidationCard
import javax.inject.Inject

class ValidateCardUseCase @Inject constructor(
    private val repo: RepositoryImpl
) {
    suspend operator fun invoke(
        cardNumber: Long,
        cardDueDate: String,
        cardSecurityCode: Int
    ): ValidationCard {
        val card = Card(0, getCardType(cardNumber), cardNumber, cardSecurityCode, cardDueDate)
        val cardNumberValid: Long
        val cardDueDateValid: String
        val cardSecurityCodeValid: Int

        when (card.type) {
            MASTERCARD -> {
                cardNumberValid = 5031755734530604
                cardDueDateValid = "11/25"
                cardSecurityCodeValid = 123
            }

            VISA -> {
                cardNumberValid = 4509953566233704
                cardDueDateValid = "11/25"
                cardSecurityCodeValid = 123
            }

            else -> {
                cardNumberValid = 371180303257522
                cardDueDateValid = "11/25"
                cardSecurityCodeValid = 1234
            }
        }

        return if (card.number != cardNumberValid)
            ValidationCard.ErrorOnCardNumber()
        else if (card.dueDate != cardDueDateValid)
            ValidationCard.ErrorOnCardDueDate()
        else if (card.securityCode != cardSecurityCodeValid)
            ValidationCard.ErrorOnCardSecurityCode()
        else if (cardAlreadyRegistered(card))
            ValidationCard.CardAlreadyRegistered
        else
            ValidationCard.CardValid(card)
    }

    private suspend fun cardAlreadyRegistered(card: Card): Boolean {
        val cardList = repo.getCardList()
        return cardList.any { it.number == card.number && it.dueDate == card.dueDate && it.securityCode == card.securityCode }
    }

    private fun getCardType(cardNumber: Long): String {
        return when (cardNumber.toString().take(1)) {
            "5" -> MASTERCARD
            "4" -> VISA
            else -> AMERICAN_EXPRESS
        }
    }

}