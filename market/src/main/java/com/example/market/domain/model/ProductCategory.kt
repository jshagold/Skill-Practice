package com.example.market.domain.model

sealed class ProductCategory(
    val categoryId: String,
    val categoryName: String,
) {
    object Etc : ProductCategory("1", "기타")

    object Tools: ProductCategory("2", "도구")

    sealed class Cloth(
        clothCategoryId: String,
        clothCategoryName: String,
    ) : ProductCategory("3_$clothCategoryId", "의류_$clothCategoryName") {
        object Shoe : Cloth("1", "신발")

        object Shirt : Cloth("2", "셔츠")
    }
}