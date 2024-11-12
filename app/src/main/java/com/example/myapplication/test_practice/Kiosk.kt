package com.example.myapplication.test_practice

import com.example.myapplication.test_practice.order.Order
import java.time.LocalDateTime

class Kiosk {

    private val beverages: MutableList<Beverage> = mutableListOf()

    fun getBeverages(): List<Beverage> = beverages.toList()

    fun add(beverage: Beverage) {
        beverages.add(beverage)
    }

    fun add(beverage: Beverage, count: Int) {
        if(count <= 0) {
            throw IllegalArgumentException("음료는 1잔 이상부터 주문할수있다.")
        }

        for(i in 0..count) {
            beverages.add(beverage)
        }
    }

    fun remove(beverage: Beverage) {
        beverages.remove(beverage)
    }

    fun clear() {
        beverages.clear()
    }

    fun calculateTotalPrice(): Int {
        var total: Int = 0

        for(beverage in beverages) {
            total += beverage.getPrice()
        }

        return total
    }

    fun createOrder(): Order {
        return Order(orderDateTime = LocalDateTime.now(), beverages = beverages)
    }
}