package com.example.market.domain.usecase

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.market.data.db.repository.BudgetRepositoryImpl
import com.example.market.domain.model.Budget
import com.example.market.domain.repository.BudgetRepository
import io.mockk.mockk
import kotlinx.coroutines.flow.Flow
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class BudgetUseCaseTest {

    lateinit var budgetRepositoryImpl: BudgetRepositoryImpl
    lateinit var budgetRepository: BudgetRepository

    val selectedDate = "202502"

    @Before
    fun setUp() {

    }

    @Test
    fun getTotalIncomeByMonth_test() {
        // Given
        val repository: BudgetRepository = mockk()
        val useCase = BudgetUseCase(repository)


        // when
        useCase.getTotalIncomeByMonth(selectedDate)

        // then

    }


}