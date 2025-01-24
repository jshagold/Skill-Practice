package com.example.market.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "budget_category")
data class BudgetCategoryEntity(
    val categoryName: String,
) {
    @PrimaryKey(autoGenerate = true)
    val categoryId: Int = 0
}