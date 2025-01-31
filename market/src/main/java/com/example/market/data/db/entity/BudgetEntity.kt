package com.example.market.data.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "budget",
    foreignKeys = [
        ForeignKey(
            entity = BudgetCategoryEntity::class,
            parentColumns = arrayOf("categoryId"),
            childColumns = arrayOf("categoryId"),
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class BudgetEntity(
    val categoryId: Int,
    val budget: Float,
    val dateTime: String,
    val inputDateTime: String,
) {
    @PrimaryKey(autoGenerate = true)
    var budgetId: Long = 0
}