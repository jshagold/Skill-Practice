package com.example.market.data.db.repository

import android.util.Log
import com.example.market.data.db.converter.toDomainModel
import com.example.market.data.db.converter.toEntity
import com.example.market.data.db.dao.BudgetCategoryDao
import com.example.market.domain.model.BudgetCategory
import com.example.market.domain.repository.BudgetCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class BudgetCategoryRepositoryImpl @Inject constructor(
    private val budgetCategoryDao: BudgetCategoryDao
) : BudgetCategoryRepository {

    override fun createCategory(category: BudgetCategory) {
        budgetCategoryDao.insertCategory(
            category.toEntity()
        )
    }

    override fun getAllCategory(): Flow<List<BudgetCategory>> {
        return flow {
            Log.e("TAG", "getAllCategory: ${budgetCategoryDao.getAllCategory()}", )
            emit(
                budgetCategoryDao.getAllCategory().map{
                    it.toDomainModel()
                }
            )
        }
    }
}