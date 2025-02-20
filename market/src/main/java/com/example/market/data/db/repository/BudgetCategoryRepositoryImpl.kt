package com.example.market.data.db.repository

import android.util.Log
import com.example.market.data.db.converter.toDomainModel
import com.example.market.data.db.converter.toEntity
import com.example.market.data.db.dao.BudgetCategoryDao
import com.example.market.data.db.entity.BudgetCategoryEntity
import com.example.market.domain.model.BudgetCategory
import com.example.market.domain.repository.BudgetCategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class BudgetCategoryRepositoryImpl @Inject constructor(
    private val budgetCategoryDao: BudgetCategoryDao
) : BudgetCategoryRepository {

    override fun createCategory(categoryName: String) {
        budgetCategoryDao.insertCategoryWithDisplayIndex(
            BudgetCategoryEntity(
                categoryName = categoryName
            )
        )
    }

    override suspend fun updateAllCategory(categoryList: List<BudgetCategory>) {
        val categoryEntities = categoryList.map {
            it.toEntity()
        }.toTypedArray()
        Log.e("TAG", "updateAllCategory: categoryEntities = ${categoryEntities.toList()}", )
        budgetCategoryDao.updateAllCategory(*categoryEntities)


//        categoryList.forEach {
//            it.toEntity()
//        }
//
//        budgetCategoryDao.updateAllCategory()

    }

    override fun getAllCategory(): Flow<List<BudgetCategory>> {
        Log.e("TAG", "getAllCategory: ${budgetCategoryDao.getAllCategory()}", )
        return budgetCategoryDao.getAllCategory().map { entityList ->
            entityList.map { entity ->
                entity.toDomainModel()
            }
        }
    }

    override fun deleteCategory(categoryId: Int) {
        budgetCategoryDao.deleteCategoryById(categoryId = categoryId)
    }
}