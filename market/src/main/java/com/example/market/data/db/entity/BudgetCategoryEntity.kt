package com.example.market.data.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "budget_category",
    indices = [Index(value = ["display_index"], unique = true)]
)
data class BudgetCategoryEntity(
    val categoryName: String,
) {
    @PrimaryKey(autoGenerate = true)
    var categoryId: Int = 0

    @ColumnInfo(name = "display_index")
    var displayIndex: Int = categoryId
}