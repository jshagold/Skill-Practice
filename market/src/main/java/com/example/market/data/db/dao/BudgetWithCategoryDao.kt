package com.example.market.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.market.data.db.entity.BudgetWithCategoryEntity

@Dao
interface BudgetWithCategoryDao {

    @Transaction
    @Query("SELECT * FROM budget_category")
    fun getAllBudgetWithCategory() : List<BudgetWithCategoryEntity>

    @Query("SELECT * FROM budget_category WHERE categoryName = :categoryName")
    fun getAllBudgetWithCategoryByCategoryName(categoryName: String): List<BudgetWithCategoryEntity>



}