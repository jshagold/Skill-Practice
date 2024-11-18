package com.example.myapplication.test_practice

import com.example.myapplication.test_practice.order.Order
import java.time.LocalDateTime
import java.time.LocalTime

class Kiosk {

    private val beverages: MutableList<Beverage> = mutableListOf()

    val openTime: LocalTime = LocalTime.of(8,0)
    val closeTime: LocalTime = LocalTime.of(22,0)

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


    fun createOrder(): Order {
        val currentDateTime = LocalDateTime.now()
        val currentTime = currentDateTime.toLocalTime()
        if(currentTime.isBefore(openTime) || currentTime.isAfter(closeTime)) {
            throw IllegalArgumentException("주문 시간이 아님")
        }


        return Order(orderDateTime = currentDateTime, beverages = beverages)
    }

    fun createOrder(currentDateTime: LocalDateTime): Order {
        val currentTime = currentDateTime.toLocalTime()
        if(currentTime.isBefore(openTime) || currentTime.isAfter(closeTime)) {
            throw IllegalArgumentException("주문 시간이 아님")
        }


        return Order(orderDateTime = currentDateTime, beverages = beverages)
    }

    fun calculateTotlaPrice(): Int {
        return beverages.stream().mapToInt(Beverage::getPrice).sum()
    }
}