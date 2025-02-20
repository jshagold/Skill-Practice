package com.example.market.domain.repository

import com.example.market.domain.model.BudgetCategory
import kotlinx.coroutines.flow.Flow

interface BudgetCategoryRepository {

    fun createCategory(categoryName: String)

    suspend fun updateAllCategory(categoryList: List<BudgetCategory>)

    fun getAllCategory(): Flow<List<BudgetCategory>>

    fun deleteCategory(categoryId: Int)

}