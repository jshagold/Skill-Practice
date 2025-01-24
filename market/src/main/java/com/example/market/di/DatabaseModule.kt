package com.example.market.di

import android.content.Context
import androidx.room.Room
import com.example.market.data.db.ApplicationDatabase
import com.example.market.data.db.dao.BudgetCategoryDao
import com.example.market.data.db.dao.BudgetDao
import com.example.market.data.db.dao.BudgetWithCategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): ApplicationDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            ApplicationDatabase::class.java,
            ApplicationDatabase.ApplicationDatabaseName
        ).build()
    }

    @Provides
    @Singleton
    fun provideBudgetDao(database: ApplicationDatabase): BudgetDao {
        return database.budgetDao()
    }

    @Provides
    @Singleton
    fun provideBudgetCategoryDao(database: ApplicationDatabase): BudgetCategoryDao {
        return database.budgetCategoryDao()
    }

    @Provides
    @Singleton
    fun provideBudgetWithCategoryDao(database: ApplicationDatabase): BudgetWithCategoryDao {
        return database.budgetWithCategoryDao()
    }


}