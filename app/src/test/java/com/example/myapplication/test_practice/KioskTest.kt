package com.example.myapplication.test_practice

import com.example.myapplication.test_practice.beverage.Americano
import com.example.myapplication.test_practice.beverage.Latte
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.ThrowableSubject
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.Test

class KioskTest {


    @Test
    fun add_menual_test() {
        val kiosk = Kiosk()
        kiosk.add(Americano())

        println(">> 음료 수 : ${kiosk.getBeverages().size}")
        println(">> 담긴 음료 : ${kiosk.getBeverages()[0].getName()}")
    }


    @Test
    fun add() {
        val kiosk = Kiosk()
        val americano = Americano()
        kiosk.add(americano)

        assertThat(kiosk.getBeverages()).hasSize(1)
        assertThat(kiosk.getBeverages()[0].getName()).isEqualTo("Americano")
    }

    @Test
    fun addSeveralBeverages() {
        val kiosk = Kiosk()
        val americano = Americano()

        kiosk.add(americano, 2)

        assertThat(kiosk.getBeverages()[0]).isEqualTo(americano)
        assertThat(kiosk.getBeverages()[1]).isEqualTo(americano)
    }

    @Test
    fun addZeroBeverages() {
        val kiosk = Kiosk()
        val americano = Americano()

        val expect = assertThrows(IllegalArgumentException::class.java) {
            kiosk.add(americano, 0)
        }

        assertThat(expect).hasMessageThat().isEqualTo("음료는 1잔 이상부터 주문할수있다.")
    }

    @Test
    fun remove() {
        val kiosk = Kiosk()
        val americano = Americano()
        kiosk.add(americano)

        kiosk.remove(americano)
        assertThat(kiosk.getBeverages()).isEmpty()
    }

    @Test
    fun clear() {
        val kiosk = Kiosk()
        val americano = Americano()
        val latte = Latte()
        kiosk.add(americano)
        kiosk.add(latte)

        kiosk.clear()
        assertThat(kiosk.getBeverages()).isEmpty()
    }


}