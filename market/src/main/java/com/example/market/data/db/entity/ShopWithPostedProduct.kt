package com.example.market.data.db.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ShopWithPostedProduct (
    @Embedded val shop: ShopEntity,
    @Relation(
        parentColumn = "shopId",
        entityColumn = "shopId"
    )
    val productList: List<PostedProductEntity>
)