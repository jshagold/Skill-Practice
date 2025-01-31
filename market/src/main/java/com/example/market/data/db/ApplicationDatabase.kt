package com.example.market.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.market.data.db.dao.BudgetCategoryDao
import com.example.market.data.db.dao.BudgetDao
import com.example.market.data.db.dao.BudgetWithCategoryDao
import com.example.market.data.db.entity.BudgetCategoryEntity
import com.example.market.data.db.entity.BudgetEntity
import com.example.market.data.db.entity.LikeProductEntity
import com.example.market.data.db.entity.PostedProductEntity
import com.example.market.data.db.entity.ShopEntity


@Database(
    entities = [
        BudgetEntity::class,
        BudgetCategoryEntity::class,
    ],
    version = 1,
)
abstract class ApplicationDatabase : RoomDatabase() {
    companion object {
        const val ApplicationDatabaseName = "app_db"
    }

    abstract fun budgetDao() : BudgetDao
    abstract fun budgetCategoryDao() : BudgetCategoryDao
    abstract fun budgetWithCategoryDao() : BudgetWithCategoryDao
}