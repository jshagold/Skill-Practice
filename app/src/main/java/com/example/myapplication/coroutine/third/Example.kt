package com.example.myapplication.coroutine.third

import android.provider.Settings.Global
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.setMain
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


fun main() {
    Example().example()
}


@OptIn(ExperimentalCoroutinesApi::class)
class Example {
    private val testDispatcher = UnconfinedTestDispatcher()
    fun example() {
        Dispatchers.setMain(testDispatcher)

        CoroutineScope(Dispatchers.Main).launch {
            suspendMain()
        }

    }

    private suspend fun suspendMain() {
        println("Before")

        suspendCoroutine<Unit> { continuation ->
            println("Before in suspend")
            continuation.resume(Unit)
        }

        val i: Int = suspendCoroutine<Int> { cont ->
            cont.resume(36)
        }
        println(i)

        println("After")
    }


}