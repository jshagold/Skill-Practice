package com.example.myapplication.test_practice.beverage

import com.example.myapplication.test_practice.Beverage

class Americano : Beverage {
    override fun getName(): String {
        return "Americano"
    }

    override fun getPrice(): Int {
        return 3000
    }
}