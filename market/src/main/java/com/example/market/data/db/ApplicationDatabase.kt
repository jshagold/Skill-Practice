package com.example.market.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.market.data.db.dao.BasketDao
import com.example.market.data.db.dao.BudgetCategoryDao
import com.example.market.data.db.dao.BudgetDao
import com.example.market.data.db.dao.BudgetWithCategoryDao
import com.example.market.data.db.dao.LikeDao
import com.example.market.data.db.dao.PostedDao
import com.example.market.data.db.dao.ShopDao
import com.example.market.data.db.entity.BasketProductEntity
import com.example.market.data.db.entity.BudgetCategoryEntity
import com.example.market.data.db.entity.BudgetEntity
import com.example.market.data.db.entity.LikeProductEntity
import com.example.market.data.db.entity.PostedProductEntity
import com.example.market.data.db.entity.ShopEntity


@Database(
    entities = [
        BasketProductEntity::class,
        LikeProductEntity::class,
        PostedProductEntity::class,
        ShopEntity::class,
        BudgetEntity::class,
        BudgetCategoryEntity::class,
    ],
    version = 1,
)
abstract class ApplicationDatabase : RoomDatabase() {
    companion object {
        const val ApplicationDatabaseName = "app_db"
    }
    abstract fun basketDao() : BasketDao
    abstract fun likeDao() : LikeDao
    abstract fun postedDao() : PostedDao
    abstract fun shopDao() : ShopDao

    abstract fun budgetDao() : BudgetDao
    abstract fun budgetCategoryDao() : BudgetCategoryDao
    abstract fun budgetWithCategoryDao() : BudgetWithCategoryDao
}