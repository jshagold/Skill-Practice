package com.example.myapplication.coroutine.sixth

import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking


fun main() {
    Example().launchBuilder()
}

class Example {

    @OptIn(DelicateCoroutinesApi::class)
    fun launchBuilder() {
        // TODO 실제로는 GlobalScope 사용을 지양해야한다.
        GlobalScope.launch {
            delay(1000L)
            println("launch1")
        }

        GlobalScope.launch {

            delay(1000L)
            println("launch2")
        }

        GlobalScope.launch {

            delay(1000L)
            println("launch3")
        }

        println("out of scope")
        Thread.sleep(2000L)
    }

    @OptIn(DelicateCoroutinesApi::class)
    fun runBlockingBuilder() = runBlocking {
        GlobalScope.launch {
            delay(1000L)
            println("launch1")
        }

        GlobalScope.launch {

            delay(1000L)
            println("launch2")
        }

        GlobalScope.launch {

            delay(1000L)
            println("launch3")
        }

        println("out of scope")
        Thread.sleep(2000L)
    }


    suspend fun useCoroutineScope() : String = coroutineScope {

        "hi"
    }
}