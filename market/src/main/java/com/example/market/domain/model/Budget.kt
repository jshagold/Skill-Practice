package com.example.market.domain.model

data class Budget(
    val budgetId: Long,
    val categoryId: Int,
    val categoryName: String,
    val budget: Float,
    val memo: String,
    val dateTime: String,
    val inputDateTime: String,
)
