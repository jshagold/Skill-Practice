package com.example.market.domain.usecase

import com.example.market.domain.model.BudgetCategory
import com.example.market.domain.repository.BudgetCategoryRepository
import javax.inject.Inject

class BudgetCategoryUseCase @Inject constructor(
    private val budgetCategoryRepository: BudgetCategoryRepository
) {

    fun createCategory(categoryName: String) {
        budgetCategoryRepository.createCategory(BudgetCategory(categoryName))
    }

}