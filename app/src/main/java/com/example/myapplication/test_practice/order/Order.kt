package com.example.myapplication.test_practice.order

import com.example.myapplication.test_practice.Beverage
import java.time.LocalDateTime

class Order(
    private val orderDateTime: LocalDateTime,
    private val beverages: List<Beverage>
) {}