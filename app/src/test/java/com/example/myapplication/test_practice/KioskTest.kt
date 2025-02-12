package com.example.myapplication.test_practice

import com.example.myapplication.test_practice.beverage.Americano
import com.example.myapplication.test_practice.beverage.Latte
import com.google.common.truth.Truth.assertThat
import com.google.common.truth.ThrowableSubject
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import java.time.LocalDateTime

class KioskTest {


    @Test
    fun testSomething() {

        val money = 1000



    }



    @Test
    fun add_menual_test() {
        val kiosk = Kiosk()
        kiosk.add(Americano())

        println(">> 음료 수 : ${kiosk.getBeverages().size}")
        println(">> 담긴 음료 : ${kiosk.getBeverages()[0].getName()}")
    }




    @DisplayName("add one beverage")
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

    @DisplayName("Calculate the total price of all beverages from the order list")
    @Test
    fun calculateTotalPrice() {
        // Given
        val kiosk = Kiosk()
        val americano = Americano()
        val latte = Latte()

        kiosk.add(americano)
        kiosk.add(latte)

        // When
        val totalPrice:Int = kiosk.calculateTotlaPrice()

        // Then
        assertThat(totalPrice).isEqualTo(7000)
    }


    @Test
    fun createOrder() {
        val kiosk = Kiosk()
        val americano = Americano()

        kiosk.add(americano)


        val order = kiosk.createOrder()

        assertThat(order.beverages).hasSize(1)
        assertThat(order.beverages[0].getName()).isEqualTo("Americano")

    }

    @Test
    fun createOrderWithTime() {
        val kiosk = Kiosk()
        val americano = Americano()

        kiosk.add(americano)

        val order = kiosk.createOrder(LocalDateTime.of(2024,11,13,10,0))

        assertThat(order.beverages).hasSize(1)
        assertThat(order.beverages[0].getName()).isEqualTo("Americano")
    }

    @Test
    fun createOrderAfterClosingTime() {
        val kiosk = Kiosk()
        val americano = Americano()

        kiosk.add(americano)

        val expectThrow = assertThrows(IllegalArgumentException::class.java) {
            kiosk.createOrder(LocalDateTime.of(2024,11,13,7,59))
        }

        assertThat(expectThrow).hasMessageThat().contains("아님")
    }



}