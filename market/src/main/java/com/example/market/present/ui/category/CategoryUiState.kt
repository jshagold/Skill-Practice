package com.example.market.present.ui.category

import com.example.market.domain.model.BudgetCategory

data class CategoryUiState(
    val categoryList: List<BudgetCategory> = listOf()
)
