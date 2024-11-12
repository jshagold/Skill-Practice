package com.example.myapplication.test_practice.beverage

import com.example.myapplication.test_practice.Beverage

class Latte : Beverage {
    override fun getName(): String {
        return "Latte"
    }

    override fun getPrice(): Int {
        return 4000
    }
}