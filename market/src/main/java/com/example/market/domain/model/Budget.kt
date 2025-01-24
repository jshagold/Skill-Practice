package com.example.market.domain.model

data class Budget(
    val budgetId: Long,
    val category: String,
    val budget: Float,
    val dateTime: String,
    val inputDateTime: String,
)
