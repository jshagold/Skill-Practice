package com.example.market.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import com.example.market.data.db.entity.BudgetCategoryEntity
import com.example.market.data.db.entity.BudgetEntity
import com.example.market.data.db.entity.BudgetWithCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetWithCategoryDao {

    @Transaction
    @Query("SELECT * FROM budget_category")
    fun getAllBudgetWithCategory() : Flow<List<BudgetWithCategoryEntity>>

    @Query("SELECT * FROM budget_category WHERE categoryName = :categoryName")
    fun getAllBudgetWithCategoryByCategoryName(categoryName: String): List<BudgetWithCategoryEntity>

    @Query("SELECT * FROM budget_category AS category INNER JOIN budget ON budget.categoryId = category.categoryId WHERE SUBSTR(budget.dateTime, 1, 6) = :month")
    fun getAllBudgetWithCategoryByMonth(month: String): Flow<Map<BudgetCategoryEntity, List<BudgetEntity>>>

    @Query(
        "SELECT * FROM budget_category AS category INNER JOIN budget ON budget.categoryId = category.categoryId WHERE budget.budget >= 0"
    )
    fun getPositiveBudget(): Flow<Map<BudgetCategoryEntity, List<BudgetEntity>>>

    @Query(
        "SELECT * FROM budget_category AS category INNER JOIN budget ON budget.categoryId = category.categoryId WHERE budget.budget < 0"
    )
    fun getNegativeBudget(): Flow<Map<BudgetCategoryEntity, List<BudgetEntity>>>



}