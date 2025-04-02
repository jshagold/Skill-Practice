package com.example.market.present.ui.budget.state

import com.example.market.domain.model.Budget
import java.time.LocalDate

data class BudgetDailyUiState(
    val date: LocalDate = LocalDate.now(),

    val totalIncome: Float = 0f,
    val allIncome: Float = 0f,
    val balance: Float = 0f,
    val budgetList: List<Budget> = listOf(),
)