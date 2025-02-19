package com.example.market.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
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
    version = 2,
)
abstract class ApplicationDatabase : RoomDatabase() {
    companion object {
        const val ApplicationDatabaseName = "app_db"
    }

    abstract fun budgetDao() : BudgetDao
    abstract fun budgetCategoryDao() : BudgetCategoryDao
    abstract fun budgetWithCategoryDao() : BudgetWithCategoryDao
}

val migration_1_2 = object : Migration(1, 2) {
    override fun migrate(db: SupportSQLiteDatabase) {
        db.execSQL(
            "ALTER TABLE budget_category Add COLUMN display_index INTEGER NOT NULL DEFAULT 0"
        )
        db.execSQL(
            "UPDATE budget_category SET display_index = categoryId"
        )
        db.execSQL(
            "CREATE UNIQUE INDEX index_budget_category_display_index ON budget_category(display_index)"
        )
    }
}