package com.example.market.data.db.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.market.data.db.entity.BudgetCategoryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface BudgetCategoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCategory(category: BudgetCategoryEntity)

    @Query("SELECT * FROM budget_category")
    fun getAllCategory(): Flow<List<BudgetCategoryEntity>>

    @Query("SELECT categoryId FROM budget_category WHERE categoryName = :categoryName")
    fun getCategoryIdByName(categoryName: String): Int

    @Update
    fun updateCategory(category: BudgetCategoryEntity)

    @Delete
    fun deleteCategory(category: BudgetCategoryEntity)

    @Query("DELETE FROM budget_category WHERE categoryId = :categoryId")
    fun deleteCategoryById(categoryId: Int)

    // todo categoryName을 enum으로 converter 사용
    @Query("DELETE FROM budget_category WHERE categoryName = :categoryName")
    fun deleteCategoryByName(categoryName: String)
}