package com.example.market.domain.usecase

import com.example.market.domain.model.Budget
import com.example.market.domain.repository.BudgetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BudgetUseCase @Inject constructor(
    private val budgetRepository: BudgetRepository
) {

    fun inputBudget(budget: Budget) {
        budgetRepository.inputBudget(budget)
    }

    fun deleteBudgetById(budgetId: Long) {
        budgetRepository.deleteBudgetById(budgetId)
    }

    fun getAllBudget(): Flow<List<Budget>> {
        return budgetRepository.getAllBudget()
    }

    fun getAllBudgetByMonthSortedByDay(month: String): Flow<List<Budget>> {
        return budgetRepository.getAllBudgetByMonth(month).map { budgetList ->
            budgetList.sortedBy { it.inputDateTime }
        }
    }

    fun getTotalIncome(): Flow<Float> {
        return budgetRepository.getTotalIncome()
    }

    fun getPositiveBudget(): Flow<List<Budget>> {
        return budgetRepository.getPositiveBudget()
    }

    fun getNegativeBudget(): Flow<List<Budget>> {
        return budgetRepository.getNegativeBudget()
    }

    fun getRemainBalance(): Flow<Float> {
        return budgetRepository.getRemainBalance()
    }


}