package com.example.eldarwallet.data.datasource.local.mapper

import com.example.eldarwallet.data.datasource.local.model.CardEntity
import com.example.eldarwallet.domain.model.Card
import javax.inject.Inject

class CardMapper @Inject constructor() {

    fun toEntity(card: Card) = CardEntity(
        id = card.id,
        type = card.type,
        number = card.number,
        securityCode = card.securityCode,
        dueDate = card.dueDate
    )

    fun fromEntity(entity: CardEntity) = Card(
        id = entity.id,
        type = entity.type,
        number = entity.number,
        securityCode = entity.securityCode,
        dueDate = entity.dueDate
    )

}