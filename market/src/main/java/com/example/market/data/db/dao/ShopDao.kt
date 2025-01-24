package com.example.market.data.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.market.data.db.entity.ShopWithPostedProduct

@Dao
interface ShopDao {

    @Query("SELECT * FROM ShopEntity")
    fun getShopsWithPostedProducts(): List<ShopWithPostedProduct>

}