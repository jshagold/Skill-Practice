package com.example.market.data.db.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.market.data.db.ApplicationDatabase
import com.example.market.data.db.dao.BudgetCategoryDao
import com.example.market.data.db.dao.BudgetDao
import com.example.market.data.db.dao.BudgetWithCategoryDao
import com.example.market.data.db.entity.BudgetCategoryEntity
import com.example.market.domain.model.Budget
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test


class BudgetRepositoryImplTest {

    lateinit var database: ApplicationDatabase
    lateinit var budgetDao: BudgetDao
    lateinit var budgetCategoryDao: BudgetCategoryDao
    lateinit var budgetWithCategoryDao: BudgetWithCategoryDao

    lateinit var budgetRepositoryImpl: BudgetRepositoryImpl

    private val categoryName1 = "Test_Category"

    @Before
    fun setUp() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            ApplicationDatabase::class.java
        ).allowMainThreadQueries().build()

        budgetDao = database.budgetDao()
        budgetCategoryDao = database.budgetCategoryDao()
        budgetWithCategoryDao = database.budgetWithCategoryDao()

        setGivenCategoryData(budgetCategoryDao)

        budgetRepositoryImpl = BudgetRepositoryImpl(budgetDao,budgetCategoryDao,budgetWithCategoryDao)
    }

    @After
    fun tearDown() {
        database.close()
    }

    private fun setGivenCategoryData(budgetCategoryDao: BudgetCategoryDao) {
        budgetCategoryDao.insertCategory(
            BudgetCategoryEntity(
                categoryName1
            )
        )
    }


    @Test
    fun getAllBudget() = runTest {
        // Given

        // When
        val budgetList = budgetRepositoryImpl.getAllBudget().first()

        // Then
        assertThat(budgetList.size).isEqualTo(0)
    }

    @Test
    fun inputBudget() = runTest {
        // Given
        val budget = Budget(
            budgetId = 0,
            categoryId = 0,
            categoryName = categoryName1,
            budget = 100f,
            memo = "memo",
            dateTime = "20250204",
            inputDateTime = "20250204",
        )

        // When
        budgetRepositoryImpl.inputBudget(budget)

        // Then
        val budgetList = budgetRepositoryImpl.getAllBudget().first()
        assertThat(budget.categoryName).isEqualTo(budgetList[0].categoryName)

    }

    @Test
    fun deleteBudget() = runTest {
        // Given
        val budget = Budget(
            budgetId = 1,
            categoryId = 0,
            categoryName = categoryName1,
            budget = 100f,
            memo = "memo",
            dateTime = "20250204",
            inputDateTime = "20250204",
        )
        budgetRepositoryImpl.inputBudget(budget)

        // When
        budgetRepositoryImpl.deleteBudget(budget)

        // Then
        val budgetList = budgetRepositoryImpl.getAllBudget().first()
        assertThat(budgetList.size).isEqualTo(0)

    }
}