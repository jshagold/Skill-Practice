package com.example.market.data.db.repository

import com.example.market.data.db.converter.toDomainModel
import com.example.market.data.db.converter.toEntity
import com.example.market.data.db.dao.BudgetCategoryDao
import com.example.market.data.db.dao.BudgetDao
import com.example.market.data.db.dao.BudgetWithCategoryDao
import com.example.market.data.db.entity.BudgetCategoryEntity
import com.example.market.data.db.entity.BudgetEntity
import com.example.market.domain.model.Budget
import com.example.market.domain.repository.BudgetRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BudgetRepositoryImpl @Inject constructor(
    private val budgetDao: BudgetDao,
    private val budgetCategoryDao: BudgetCategoryDao,
    private val budgetWithCategoryDao: BudgetWithCategoryDao,
) : BudgetRepository {

    override fun getAllBudget(): Flow<List<Budget>> {
        val budgetWithCategoryList = budgetWithCategoryDao.getAllBudgetWithCategory()

        val budgetList = budgetWithCategoryList.map { budgetCategoryList ->
            budgetCategoryList.flatMap { budgetCategory ->
                budgetCategory.budgets.map { budgetEntity -> budgetEntity.toDomainModel(budgetCategory.category) }
            }
        }

        return budgetList
    }

    override fun getAllBudgetByMonth(month: String): Flow<List<Budget>> {
        val budgetWithCategoryList = budgetWithCategoryDao.getAllBudgetWithCategoryByMonth(month)

        return budgetWithCategoryList.map { map: Map<BudgetCategoryEntity, List<BudgetEntity>> ->
            map.flatMap { (budgetCategoryEntity, budgetEntityList) ->
                budgetEntityList.map { budgetEntity ->
                    budgetEntity.toDomainModel(budgetCategoryEntity)
                }
            }
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
        budgetDao.insertBudget(budget = budget.toEntity())
    }

    override fun deleteBudget(budget: Budget) {
        budgetDao.deleteBudget(budget = budget.toEntity())
    }

    override fun deleteBudgetById(budgetId: Long) {
        budgetDao.deleteBudgetByBudgetId(budgetId = budgetId)
    }
}