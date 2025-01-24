package com.example.market.domain.repository

import com.example.market.domain.model.BudgetCategory
import kotlinx.coroutines.flow.Flow

interface BudgetCategoryRepository {

    fun createCategory(category: BudgetCategory)

    fun getAllCategory(): Flow<List<BudgetCategory>>

}