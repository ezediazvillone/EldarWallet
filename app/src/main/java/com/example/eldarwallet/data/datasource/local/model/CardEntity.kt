package com.example.eldarwallet.data.datasource.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "type") val type: String,
    @ColumnInfo(name = "number") val number: String,
    @ColumnInfo(name = "securityCode") val securityCode: Int,
    @ColumnInfo(name = "dueDate") val dueDate: String
)
