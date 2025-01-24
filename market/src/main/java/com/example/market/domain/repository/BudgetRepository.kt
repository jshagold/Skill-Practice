package com.example.market.domain.repository

import com.example.market.domain.model.Budget
import kotlinx.coroutines.flow.Flow

interface BudgetRepository {

    fun getAllBudget() : Flow<List<Budget>>

    fun inputBudget(budget: Budget)

    fun deleteBudget(budget: Budget)
}