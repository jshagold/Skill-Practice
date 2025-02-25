package com.example.market.domain.model.enums

import androidx.compose.ui.graphics.Color
import com.example.market.R
import com.example.market.present.theme.ExpenseTheme
import com.example.market.present.theme.IncomeTheme

enum class BudgetType(
    val index: Int,
    val stringResource: Int,
    val color: Color,
) {
    INCOME(0, R.string.budget_type_income, IncomeTheme), EXPENSE(1, R.string.budget_type_expense, ExpenseTheme)
}