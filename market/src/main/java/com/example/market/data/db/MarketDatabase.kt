package com.example.market.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.market.data.db.dao.BasketDao
import com.example.market.data.db.dao.LikeDao
import com.example.market.data.db.dao.PostedDao
import com.example.market.data.db.dao.ShopDao
import com.example.market.data.db.model.BasketProductEntity
import com.example.market.data.db.model.LikeProductEntity
import com.example.market.data.db.model.PostedProductEntity
import com.example.market.data.db.model.ShopEntity


@Database(
    entities = [
        BasketProductEntity::class,
        LikeProductEntity::class,
        PostedProductEntity::class,
        ShopEntity::class,
    ],
    version = 1,
)
abstract class MarketDatabase : RoomDatabase() {
    companion object {
        const val MarketDbName = "market_db"
    }
    abstract fun basketDao() : BasketDao
    abstract fun likeDao() : LikeDao
    abstract fun postedDao() : PostedDao
    abstract fun shopDao() : ShopDao
}