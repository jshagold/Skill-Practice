package com.example.market.domain.usecase

import com.example.market.domain.model.BudgetCategory
import com.example.market.domain.repository.BudgetCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BudgetCategoryUseCase @Inject constructor(
    private val budgetCategoryRepository: BudgetCategoryRepository
) {

    fun createCategory(categoryName: String) {
        budgetCategoryRepository.createCategory(categoryName)
    }

    suspend fun updateAllCategory(categoryList: List<BudgetCategory>) {
        budgetCategoryRepository.updateAllCategory(categoryList)
    }

    fun getAllCategory(): Flow<List<BudgetCategory>> {

        return budgetCategoryRepository.getAllCategory().map {
            it.sortedBy { budgetCategory ->  budgetCategory.displayIndex }
        }
    }

    fun deleteCategory(categoryId: Int) {
        budgetCategoryRepository.deleteCategory(categoryId = categoryId)
    }
}