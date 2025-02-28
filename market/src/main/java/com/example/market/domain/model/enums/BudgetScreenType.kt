package com.example.market.domain.model.enums

import com.example.market.R

enum class BudgetScreenType(
    val index: Int,
    val stringResource: Int,
) {
    DAILY(
        0,
        R.string.budget_screen_type_daily
    ),
    MONTHLY(
        1,
        R.string.budget_screen_type_monthly
    ),
    CALENDAR(
        2,
        R.string.budget_screen_type_calendar
    ),
}