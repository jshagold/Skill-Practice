package com.example.market.di

import com.example.market.data.db.repository.BudgetCategoryRepositoryImpl
import com.example.market.data.db.repository.BudgetRepositoryImpl
import com.example.market.domain.repository.BudgetCategoryRepository
import com.example.market.domain.repository.BudgetRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface DataModule {

    @Binds
    @Singleton
    fun bindBudgetRepository(budgetRepositoryImpl: BudgetRepositoryImpl): BudgetRepository

    @Binds
    @Singleton
    fun bindBudgetCategoryRepository(budgetCategoryRepositoryImpl: BudgetCategoryRepositoryImpl): BudgetCategoryRepository

}