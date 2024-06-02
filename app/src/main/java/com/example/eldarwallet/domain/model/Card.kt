package com.example.eldarwallet.domain.model

data class Card(
    val id: Int,
    val type: String,
    val number: Long,
    val securityCode: Int,
    val dueDate: String
)