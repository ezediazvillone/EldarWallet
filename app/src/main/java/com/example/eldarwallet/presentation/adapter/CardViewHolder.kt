package com.example.eldarwallet.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.eldarwallet.R
import com.example.eldarwallet.databinding.ItemCardBinding
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.util.CardType.MASTERCARD
import com.example.eldarwallet.domain.util.CardType.VISA

class CardViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemCardBinding.bind(view)

    fun render(
        card: Card,
        onCardClick: (Card) -> Unit
    ) {
        setCardType(card.type)
        setCardNumber(card.number)
        setCardDueDate(card.dueDate)
        binding.cardCl.setOnClickListener { onCardClick(card) }
    }

    private fun setCardDueDate(dueDate: String) = with(binding.cardDueDate) {
        text = dueDate
    }

    private fun setCardNumber(number: Long) = with(binding.cardNumber) {
        text = number.toString()
    }

    private fun setCardType(type: String) = with(binding.cardType) {
        val cardTypeImage = when (type) {
            MASTERCARD -> R.drawable.ic_mastercard
            VISA -> R.drawable.ic_visa
            else -> R.drawable.ic_american_express
        }
        setImageResource(cardTypeImage)
    }

}