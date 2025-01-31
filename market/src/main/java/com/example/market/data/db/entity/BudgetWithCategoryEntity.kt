package com.example.market.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class BudgetWithCategoryEntity(
    @Embedded
    val category: BudgetCategoryEntity,
    @Relation(
        parentColumn = "categoryId",
        entityColumn = "categoryId"
    )
    val budgets: List<BudgetEntity>
) {
}