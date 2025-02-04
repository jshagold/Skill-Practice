package com.example.market.data.db.repository

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.filters.MediumTest
import androidx.test.filters.SmallTest
import com.example.market.data.db.ApplicationDatabase
import com.example.market.data.db.dao.BudgetCategoryDao
import com.example.market.data.db.dao.BudgetDao
import com.example.market.data.db.dao.BudgetWithCategoryDao
import com.example.market.data.db.entity.BudgetCategoryEntity
import com.example.market.data.db.entity.BudgetEntity
import com.example.market.domain.model.Budget
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class BudgetRepositoryImplTest {


//    val budgetRepository: BudgetRepositoryImpl = mock()
    

    lateinit var database: ApplicationDatabase





    lateinit var budgetDao: BudgetDao
    lateinit var budgetCategoryDao: BudgetCategoryDao
    lateinit var budgetWithCategoryDao: BudgetWithCategoryDao

    // Given
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
    }

    @After
    fun tearDown() {
        database.close()
    }

    private fun setGivenCategoryData(budgetCategoryDao: BudgetCategoryDao) {
        budgetCategoryDao.insertCategory(BudgetCategoryEntity(
            categoryName1
        ))
    }


    @Test
    fun getAllBudget() {
        // Given

        // When
        val allList = budgetWithCategoryDao.getAllBudgetWithCategory()

        // Then
        assertThat(allList.size).isEqualTo(1)



    }

    @Test
    fun inputBudget() = runTest {
        // Given
        val category: BudgetCategoryEntity = budgetCategoryDao.getCategoryByName(categoryName1).take(1).toList()[0]
        val insert = BudgetEntity(
            categoryId = category.categoryId,
            budget = 100f,
            memo = "test budget 1",
            dateTime = "20250203000000",
            inputDateTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")),
        )

        // When
        budgetDao.insertBudget(insert)

        // Then
        assertThat(insert).isEqualTo(budgetDao.getAllBudget()[0])
    }

    @Test
    fun deleteBudget() {
    }
}