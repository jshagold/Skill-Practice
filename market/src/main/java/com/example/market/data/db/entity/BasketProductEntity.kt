package com.example.market.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.market.domain.model.ProductCategory
import com.example.market.domain.model.SalesStatus
import com.example.market.domain.model.Shipping

@Entity(tableName = "basket_product")
data class BasketProductEntity (
    @PrimaryKey
    val productId: String,
    val productName: String,
    val imageUrl: String,
    val price: String,
    val category: ProductCategory,
    val salesStatus: SalesStatus,
    val shopId: String,
    val isNew: Boolean,
    val shipping: Shipping
)