package com.example.eldarwallet.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.model.Payment
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.domain.usecase.FormatNumberUseCase
import com.example.eldarwallet.domain.usecase.GetCardListUseCase
import com.example.eldarwallet.domain.usecase.GetDebtUseCase
import com.example.eldarwallet.domain.usecase.GetPaymentsUseCase
import com.example.eldarwallet.domain.usecase.GetUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getDebtUseCase: GetDebtUseCase,
    private val getFormatNumberUseCase: FormatNumberUseCase,
    private val getCardListUseCase: GetCardListUseCase,
    private val getPaymentsUseCase: GetPaymentsUseCase
) : ViewModel() {

    private val _cards = MutableLiveData<List<Card>>()
    val cards: LiveData<List<Card>> = _cards

    fun getUser(): User {
        val user = getUserUseCase()
        Log.d("MainViewModel", "getUser: $user")
        return user
    }

    fun getDebtFormatted(): String {
        val debt = getDebtUseCase()
        Log.d("MainViewModel", "debt: $debt")
        val debtFormatted = getFormatNumberUseCase(debt)
        Log.d("MainViewModel", "debtFormatted: $debtFormatted")
        return debtFormatted
    }

    fun getCards() {
        viewModelScope.launch {
            val cards = getCardListUseCase()
            Log.d("MainViewModel", "cards: $cards")
            _cards.value = cards
        }
    }

    fun getPayments(): List<Payment> {
        val payments = getPaymentsUseCase()
        Log.d("MainViewModel", "payments: $payments")
        return payments
    }

}