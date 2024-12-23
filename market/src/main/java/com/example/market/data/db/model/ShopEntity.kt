package com.example.market.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ShopEntity (
    @PrimaryKey
    val shopId: String,
    val shopName: String,
    val ownerName: String,
)