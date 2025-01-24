package com.example.market.data.db.converter

import com.example.market.data.db.entity.BudgetCategoryEntity
import com.example.market.data.db.entity.BudgetEntity
import com.example.market.data.db.entity.BudgetWithCategoryEntity
import com.example.market.domain.model.Budget
import com.example.market.domain.model.BudgetCategory


fun BudgetEntity.toDomainModel(categoryName: String) : Budget {
    return Budget(
        budgetId = budgetId,
        category = categoryName,
        budget = budget,
        dateTime = dateTime,
        inputDateTime = inputDateTime,
    )
}

fun BudgetWithCategoryEntity.toBudgetList() : List<Budget> {
    val categoryName = this.category.categoryName

    return this.budgets.map {
        Budget(
            budgetId = it.budgetId,
            category = categoryName,
            budget = it.budget,
            dateTime = it.dateTime,
            inputDateTime = it.inputDateTime,
        )
    }
}

fun BudgetCategory.toEntity() : BudgetCategoryEntity {
    return BudgetCategoryEntity(
        categoryName = this.categoryName
    )
}

fun BudgetCategoryEntity.toDomainModel() : BudgetCategory {
    return BudgetCategory(
        categoryName = this.categoryName
    )
}