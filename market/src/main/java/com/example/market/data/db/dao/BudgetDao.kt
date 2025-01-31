package com.example.market.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.market.data.db.entity.BudgetEntity

@Dao
interface BudgetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBudget(budget: BudgetEntity)

    @Query("SELECT * FROM budget")
    fun getAllBudget(): List<BudgetEntity>

    @Update
    fun updateBudget(budget: BudgetEntity)

    @Delete
    fun deleteBudget(budget: BudgetEntity)

    @Query("DELETE FROM budget WHERE budgetId = :budgetId")
    fun deleteBudgetByBudgetId(budgetId: Long)
}