package com.example.market.domain.model

sealed class Shipping(val price: Float) {
    data object Free: Shipping(0f)

    class Priced(input: Float) : Shipping(input)
}