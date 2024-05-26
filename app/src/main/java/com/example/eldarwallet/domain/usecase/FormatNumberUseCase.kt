package com.example.eldarwallet.domain.usecase

import java.text.NumberFormat
import javax.inject.Inject

class FormatNumberUseCase @Inject constructor() {

    operator fun invoke(number: Double): String {
        return NumberFormat.getCurrencyInstance().format(number)
    }

}