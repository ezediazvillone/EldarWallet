package com.example.eldarwallet.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.eldarwallet.R
import com.example.eldarwallet.databinding.ItemPaymentBinding
import com.example.eldarwallet.domain.model.Payment

class PaymentViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    private val binding = ItemPaymentBinding.bind(view)

    fun render(
        payment: Payment,
        onPaymentClick: (Payment) -> Unit
    ) {
        setPaymentTypeImage(payment.type)
        setPaymentTypeText(payment.type)
        binding.paymentCl.setOnClickListener { onPaymentClick(payment) }
    }

    private fun setPaymentTypeText(type: String) = with(binding.paymentTvType) {
        text = type
    }

    private fun setPaymentTypeImage(type: String) = with(binding.paymentIvType) {
        val paymentTypeImage = when (type) {
            "QR" -> R.drawable.ic_qr
            else -> R.drawable.ic_nfc
        }
        setImageResource(paymentTypeImage)
    }

}