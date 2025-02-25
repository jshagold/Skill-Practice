package com.example.market.data.db.converter

import com.example.market.data.db.entity.BudgetCategoryEntity
import com.example.market.data.db.entity.BudgetEntity
import com.example.market.data.db.entity.BudgetWithCategoryEntity
import com.example.market.domain.model.Budget
import com.example.market.domain.model.BudgetCategory


fun Budget.toEntity() : BudgetEntity {
    return BudgetEntity(
        categoryId = categoryId,
        budget = budget,
        memo = memo,
        dateTime = dateTime,
        inputDateTime = inputDateTime
    )
}

fun BudgetEntity.toDomainModel(category: BudgetCategoryEntity) : Budget {
    return Budget(
        budgetId = budgetId,
        categoryId = category.categoryId,
        categoryName = category.categoryName,
        budget = budget,
        memo = memo,
        dateTime = dateTime,
        inputDateTime = inputDateTime,
    )
}

fun BudgetWithCategoryEntity.toBudgetList() : List<Budget> {
    return this.budgets.map {
        Budget(
            budgetId = it.budgetId,
            categoryId = this.category.categoryId,
            categoryName = this.category.categoryName,
            budget = it.budget,
            memo = it.memo,
            dateTime = it.dateTime,
            inputDateTime = it.inputDateTime,
        )
    }
}

fun BudgetCategory.toEntity() : BudgetCategoryEntity {
    val entity = BudgetCategoryEntity(
        categoryName = this.categoryName,
        displayIndex = this.displayIndex
    )
    entity.categoryId = this.categoryId

    return entity
}

fun BudgetCategoryEntity.toDomainModel() : BudgetCategory {
    return BudgetCategory(
        categoryId = this.categoryId,
        categoryName = this.categoryName,
        displayIndex = this.displayIndex,
    )
}