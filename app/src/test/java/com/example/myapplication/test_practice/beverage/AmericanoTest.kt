package com.example.myapplication.test_practice.beverage

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import com.google.common.truth.Truth.assertThat


class AmericanoTest {

    @Test
    fun getName() {
         val americano = Americano()


//        assertEquals("Americano", americano.getName())
        assertThat(americano.getName()).isEqualTo("Americano")
    }

    @Test
    fun getPrice() {
        val americano = Americano()

        assertThat(americano.getPrice()).isEqualTo(3000)
    }
}