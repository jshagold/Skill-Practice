package com.example.market.data.db.repository

import com.example.market.data.db.converter.toBudgetList
import com.example.market.data.db.converter.toDomainModel
import com.example.market.data.db.dao.BudgetCategoryDao
import com.example.market.data.db.dao.BudgetDao
import com.example.market.data.db.dao.BudgetWithCategoryDao
import com.example.market.data.db.entity.BudgetEntity
import com.example.market.data.db.entity.BudgetWithCategoryEntity
import com.example.market.domain.model.Budget
import com.example.market.domain.repository.BudgetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BudgetRepositoryImpl @Inject constructor(
    private val budgetDao: BudgetDao,
    private val budgetCategoryDao: BudgetCategoryDao,
    private val budgetWithCategoryDao: BudgetWithCategoryDao,
) : BudgetRepository {

    override fun getAllBudget(): Flow<List<Budget>> {
        return flow {
            val budgetWithCategoryList: List<BudgetWithCategoryEntity> = budgetWithCategoryDao.getAllBudgetWithCategory()
            val budgetList: MutableList<Budget> = mutableListOf()

            budgetWithCategoryList.forEach {
                budgetList.addAll(it.toBudgetList())
            }

            emit(
                budgetList.toList()
            )
        }
    }

    override fun getPositiveBudget(): Flow<List<Budget>> {
        return budgetWithCategoryDao.getPositiveBudget().map { map ->
            val budgetList = map.flatMap { (budgetCategoryE, budgetListE) ->
                budgetListE.map {
                    it.toDomainModel(budgetCategoryE)
                }
            }

            budgetList
        }
    }

    override fun getNegativeBudget(): Flow<List<Budget>> {
        return budgetWithCategoryDao.getNegativeBudget().map { map ->
            val budgetList = map.flatMap { (budgetCategoryE, budgetListE) ->
                budgetListE.map {
                    it.toDomainModel(budgetCategoryE)
                }
            }

            budgetList
        }
    }

    override fun getTotalIncome(): Flow<Float> {
        return budgetDao.getTotalIncome()
    }

    override fun getRemainBalance(): Flow<Float> {
        return budgetDao.getRemainBalance()
    }

    override fun inputBudget(budget: Budget) {

        val categoryId = budgetCategoryDao.getCategoryIdByName(budget.categoryName)

        budgetDao.insertBudget(budget = BudgetEntity(
            categoryId = categoryId,
            budget = budget.budget,
            memo = budget.memo,
            dateTime = budget.dateTime,
            inputDateTime = budget.inputDateTime
        ))
    }

    override fun deleteBudget(budget: Budget) {
        budgetDao.deleteBudgetByBudgetId(budgetId = budget.budgetId)
    }
}