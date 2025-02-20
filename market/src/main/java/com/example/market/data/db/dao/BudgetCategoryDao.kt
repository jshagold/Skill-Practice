package com.example.market.data.db.dao

import android.provider.SyncStateContract.Helpers.insert
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.market.data.db.entity.BudgetCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: BudgetCategoryEntity): Long

    @Query("UPDATE budget_category SET display_index = categoryId WHERE categoryId = :id")
    fun updateDisplayIndex(id: Int)

    @Transaction
    fun insertCategoryWithDisplayIndex(category: BudgetCategoryEntity) {
        val id = insertCategory(category)
        updateDisplayIndex(id.toInt())
    }

    @Update
    suspend fun updateCategory(category: BudgetCategoryEntity)

    @Update
    suspend fun updateAllCategory(vararg category: BudgetCategoryEntity)

    @Query("SELECT * FROM budget_category")
    fun getAllCategory(): Flow<List<BudgetCategoryEntity>>

    @Query("SELECT * FROM budget_category WHERE categoryName = :categoryName")
    fun getCategoryByName(categoryName: String): Flow<BudgetCategoryEntity>

    @Query("SELECT categoryId FROM budget_category WHERE categoryName = :categoryName")
    fun getCategoryIdByName(categoryName: String): Int

    @Delete
    fun deleteCategory(category: BudgetCategoryEntity)

    @Query("DELETE FROM budget_category WHERE categoryId = :categoryId")
    fun deleteCategoryById(categoryId: Int)

    // todo categoryName을 enum으로 converter 사용
    @Query("DELETE FROM budget_category WHERE categoryName = :categoryName")
    fun deleteCategoryByName(categoryName: String)
}