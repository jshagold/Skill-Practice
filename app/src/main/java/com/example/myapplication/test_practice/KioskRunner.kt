package com.example.myapplication.test_practice

import com.example.myapplication.test_practice.beverage.Americano
import com.example.myapplication.test_practice.beverage.Latte
import com.example.myapplication.test_practice.order.Order


fun main() {
    val runner = KioskRunner()

    runner.main()
}

class KioskRunner {

    fun main() {
        val kiosk = Kiosk()
        kiosk.add(Americano())
        println("+++ 아메리카노")

        kiosk.add(Latte())
        println("+++ 라떼")

        val totalPrice = kiosk.calculateTotalPrice()
        println("총 가격  $totalPrice")
    }

}