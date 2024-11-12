package com.example.myapplication.test_practice.order

import com.example.myapplication.test_practice.Beverage
import java.time.LocalDateTime

class Order(
    val orderDateTime: LocalDateTime,
    val beverages: List<Beverage>
) {}