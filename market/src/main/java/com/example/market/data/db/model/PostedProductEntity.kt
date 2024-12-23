package com.example.market.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.market.domain.model.ProductCategory
import com.example.market.domain.model.SalesStatus
import com.example.market.domain.model.Shipping
import com.example.market.domain.model.Shop


@Entity(tableName = "posted_product")
data class PostedProductEntity (
    @PrimaryKey
    val productId: String,
    val productName: String,
    val imageUrl: String,
    val price: String,
    val category: ProductCategory,
    val salesStatus: SalesStatus,
    val shop: Shop,
    val isNew: Boolean,
    val shipping: Shipping
)