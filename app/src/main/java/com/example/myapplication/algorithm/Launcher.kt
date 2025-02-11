package com.example.myapplication.algorithm

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))

//    val (x, target) = readln().split(" ").map { it.toInt() }
//    val cardList = readln().split(" ").map { it.toInt() }
//
//    println("${BJ2798(targetNum = target, cardList = cardList).main()}")



//    BJ4673().main()


    BJ1065().main(br.readLine().toInt())
}