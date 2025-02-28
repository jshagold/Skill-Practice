package com.example.market.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.market.data.db.entity.BudgetEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBudget(budget: BudgetEntity)

    @Query("SELECT * FROM budget")
    fun getAllBudget(): List<BudgetEntity>

    @Query("SELECT * FROM budget WHERE SUBSTR(dateTime, 5, 2) = :month")
    fun getAllBudgetByMonth(month: String): Flow<List<BudgetEntity>>

    @Query("SELECT * FROM budget WHERE budget >= 0")
    fun getPositiveBudget(): Flow<List<BudgetEntity>>

    @Query("SELECT * FROM budget WHERE budget < 0")
    fun getNegativeBudget(): Flow<List<BudgetEntity>>

    @Query("SELECT SUM(budget) FROM budget WHERE budget >= 0")
    fun getTotalIncome(): Flow<Float>

    @Query("SELECT SUM(budget) FROM budget")
    fun getRemainBalance(): Flow<Float>

    @Update
    fun updateBudget(budget: BudgetEntity)

    @Delete
    fun deleteBudget(budget: BudgetEntity)

    @Query("DELETE FROM budget WHERE budgetId = :budgetId")
    fun deleteBudgetByBudgetId(budgetId: Long)
}