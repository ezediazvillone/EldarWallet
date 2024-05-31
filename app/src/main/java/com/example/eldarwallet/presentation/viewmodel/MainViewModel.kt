package com.example.eldarwallet.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.eldarwallet.domain.model.Card
import com.example.eldarwallet.domain.model.Payment
import com.example.eldarwallet.domain.model.User
import com.example.eldarwallet.domain.usecase.FormatNumberUseCase
import com.example.eldarwallet.domain.usecase.GetCardsUseCase
import com.example.eldarwallet.domain.usecase.GetDebtUseCase
import com.example.eldarwallet.domain.usecase.GetPaymentsUseCase
import com.example.eldarwallet.domain.usecase.GetUserUseCase
import com.example.eldarwallet.domain.usecase.SaveUserUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getUserUseCase: GetUserUseCase,
    private val getDebtUseCase: GetDebtUseCase,
    private val getFormatNumberUseCase: FormatNumberUseCase,
    private val getCardsUseCase: GetCardsUseCase,
    private val getPaymentsUseCase: GetPaymentsUseCase
) : ViewModel() {

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

    fun getCards(): List<Card> {
        val cards = getCardsUseCase()
        Log.d("MainViewModel", "cards: $cards")
        return cards
    }

    fun getPayments(): List<Payment> {
        val payments = getPaymentsUseCase()
        Log.d("MainViewModel", "payments: $payments")
        return payments
    }

}