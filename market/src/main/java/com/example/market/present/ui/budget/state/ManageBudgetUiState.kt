package com.example.market.present.ui.budget.state

import com.example.market.domain.model.Budget
import com.example.market.domain.model.BudgetCategory
import java.time.LocalDateTime

data class ManageBudgetUiState(
    val categoryList: List<BudgetCategory> = listOf(),
    val budgetList: List<Budget> = listOf(),
    val selectedCategory: BudgetCategory? = null,
    val selectedDateTime: LocalDateTime? = null,

    val isOpenCategoryBottomSheet: Boolean = false,
    val isOpenDateTimePicker: Boolean = false,
)