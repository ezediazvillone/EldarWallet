package com.example.eldarwallet.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.usecase.GetCardByIdUseCase
import com.example.eldarwallet.domain.usecase.InsertCardUseCase
import com.example.eldarwallet.domain.usecase.ValidateCardUseCase
import com.example.eldarwallet.domain.util.ValidationCard
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CardViewModel @Inject constructor(
    private val validateCardUseCase: ValidateCardUseCase,
    private val getCardByIdUseCase: GetCardByIdUseCase,
    private val insertCardUseCase: InsertCardUseCase
) : ViewModel() {

    private val _card = MutableLiveData<Card>()
    val card: LiveData<Card> = _card

    private val _cardValidation = MutableLiveData<ValidationCard>()
    val cardValidation: LiveData<ValidationCard> = _cardValidation

    private val _cardRegisteredSuccessfully = MutableLiveData<Boolean>()
    val cardRegisteredSuccessfully: LiveData<Boolean> = _cardRegisteredSuccessfully

    var cardDetail = false

    fun getCardById(cardId: Int) {
        viewModelScope.launch {
            val card = getCardByIdUseCase(cardId)
            Log.d("CardViewModel", "card: $card")
            _card.value = card
        }
    }

    fun validateCard(
        cardNumber: Long,
        cardDueDate: String,
        cardSecurityCode: Int
    ) {
        viewModelScope.launch {
            val validation = validateCardUseCase(cardNumber, cardDueDate, cardSecurityCode)
            Log.d("CardViewModel", "cardValidation: $validation")
            _cardValidation.value = validation
        }
    }

    fun insertCard(card: Card) {
        viewModelScope.launch {
            val insertSuccessfully = insertCardUseCase(card)
            Log.d("CardViewModel", "card inserted successfully: $insertSuccessfully")
            _cardRegisteredSuccessfully.value = insertSuccessfully
        }
    }

}