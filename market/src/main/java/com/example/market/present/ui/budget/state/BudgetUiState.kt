package com.example.market.present.ui.budget.state

import com.example.market.domain.model.Budget

data class BudgetUiState(
    val totalIncome: Float = 0f,
    val balance: Float = 0f,
    val budgetList: List<Budget> = listOf(),
)