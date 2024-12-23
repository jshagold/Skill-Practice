package com.example.market.data.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.market.data.db.model.PostedProductEntity

@Dao
interface PostedDao {
    @Query("SELECT * FROM posted_product")
    suspend fun getAll() : List<PostedProductEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item : PostedProductEntity)

    @Query("DELETE FROM posted_product WHERE productId=:id")
    suspend fun delete(id: String)
}