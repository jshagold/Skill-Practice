package com.example.market.present.ui.budget.state

import com.example.market.domain.model.Budget
import com.example.market.domain.model.enums.BudgetDateType
import java.time.LocalDate

data class BudgetUiState(
    val dateType: BudgetDateType = BudgetDateType.MONTH,
    val selectedDate: LocalDate = LocalDate.now(),

    val isOpenMonthPicker: Boolean = false,


    val totalIncome: Float = 0f,
    val balance: Float = 0f,
    val budgetList: List<Budget> = listOf(),
)