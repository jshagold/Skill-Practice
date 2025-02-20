package com.example.market.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "budget_category",
)
data class BudgetCategoryEntity(
    val categoryName: String,
    @ColumnInfo(name = "display_index")
    var displayIndex: Int = 0
) {
    @PrimaryKey(autoGenerate = true)
    var categoryId: Int = 0

}