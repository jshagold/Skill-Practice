package com.example.market.domain.usecase

import com.example.market.domain.model.BudgetCategory
import com.example.market.domain.repository.BudgetCategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BudgetCategoryUseCase @Inject constructor(
    private val budgetCategoryRepository: BudgetCategoryRepository
) {

    fun createCategory(categoryName: String) {
        budgetCategoryRepository.createCategory(BudgetCategory(categoryName))
    }

    fun getAllCategory(): Flow<List<BudgetCategory>> {
        return budgetCategoryRepository.getAllCategory()
    }
}