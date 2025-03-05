package com.example.market.domain.repository

import com.example.market.domain.model.Budget
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {

    fun getAllBudget() : Flow<List<Budget>>

    /**
     * 입력받은 연월의 모든 Budget List
     * @param month String / yyyyMM 포맷
     * @return Flow<List<Budget>>
     */
    fun getAllBudgetByMonth(month: String): Flow<List<Budget>>

    fun getPositiveBudget(): Flow<List<Budget>>

    fun getNegativeBudget(): Flow<List<Budget>>

    fun getTotalIncome(): Flow<Float>

    /**
     * 입력받은 연월의 모든 수입(0이상)의 총합
     * @param month String / yyyyMM
     * @return Flow<Float>
     */
    fun getTotalIncomeByMonth(month: String): Flow<Float>

    fun getRemainBalance(): Flow<Float>

    fun inputBudget(budget: Budget)

    fun deleteBudget(budget: Budget)

    fun deleteBudgetById(budgetId: Long)
}