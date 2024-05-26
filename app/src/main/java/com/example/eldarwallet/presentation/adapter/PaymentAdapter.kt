package com.example.eldarwallet.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.eldarwallet.R
import com.example.eldarwallet.domain.model.Payment

class PaymentAdapter(
    private val paymentList: List<Payment>,
    private val onPaymentClick: (Payment) -> Unit
) : RecyclerView.Adapter<PaymentViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PaymentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return PaymentViewHolder(layoutInflater.inflate(R.layout.item_payment, parent, false))
    }

    override fun onBindViewHolder(holder: PaymentViewHolder, position: Int) {
        val item = paymentList[position]
        holder.render(item, onPaymentClick)
    }

    override fun getItemCount() = paymentList.size

}