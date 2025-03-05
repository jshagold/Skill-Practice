package com.example.market.domain.usecase

import com.example.market.domain.model.Budget
import com.example.market.domain.repository.BudgetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BudgetUseCase @Inject constructor(
    private val budgetRepository: BudgetRepository
) {

    /**
     * 예산 입력
     */
    fun inputBudget(budget: Budget) {
        budgetRepository.inputBudget(budget)
    }

    /**
     * 예산 삭제
     */
    fun deleteBudgetById(budgetId: Long) {
        budgetRepository.deleteBudgetById(budgetId)
    }

    /**
     * 기록된 모든 예산 get
     */
    fun getAllBudget(): Flow<List<Budget>> {
        return budgetRepository.getAllBudget()
    }

    /**
     * 입력받은 월의 모든 예산 get
     */
    fun getAllBudgetByMonthSortedByDay(month: String): Flow<List<Budget>> {
        return budgetRepository.getAllBudgetByMonth(month).map { budgetList ->
            budgetList.sortedBy { it.inputDateTime }
        }
    }

    /**
     * 기록된 모든 수입의 총합
     * @return Float
     */
    fun getTotalIncome(): Flow<Float> {
        return budgetRepository.getTotalIncome()
    }

    /**
     * 입력받은 월의 모든 수입 총합
     */
    fun getTotalIncomeByMonth(month: String): Flow<Float> {
        return budgetRepository.getTotalIncomeByMonth(month)
    }

    /**
     * 기록된 모든 지출 총합
     */
    fun getTotalExpense(): Flow<Float> {
        return budgetRepository.getTotalIncome()
    }


}