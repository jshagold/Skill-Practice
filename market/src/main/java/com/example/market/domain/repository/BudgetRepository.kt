package com.example.market.domain.repository

import com.example.market.domain.model.Budget
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {

    fun getAllBudget() : Flow<List<Budget>>

    /**
     * @month: String - yyyyMM 포맷
     */
    fun getAllBudgetByMonth(month: String): Flow<List<Budget>>

    fun getPositiveBudget(): Flow<List<Budget>>

    fun getNegativeBudget(): Flow<List<Budget>>

    fun getTotalIncome(): Flow<Float>

    fun getRemainBalance(): Flow<Float>

    fun inputBudget(budget: Budget)

    fun deleteBudget(budget: Budget)

    fun deleteBudgetById(budgetId: Long)
}