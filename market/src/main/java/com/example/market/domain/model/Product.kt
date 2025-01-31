package com.example.market.domain.model

data class Product(
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