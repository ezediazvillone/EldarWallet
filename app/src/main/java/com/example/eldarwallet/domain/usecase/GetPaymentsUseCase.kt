package com.example.eldarwallet.domain.usecase

import com.example.eldarwallet.domain.model.Payment
import javax.inject.Inject

class GetPaymentsUseCase @Inject constructor() {

    operator fun invoke(): List<Payment> {
        return listOf(Payment(id = 1, type = "QR"), Payment(id = 2, type = "NFC"))
    }

}