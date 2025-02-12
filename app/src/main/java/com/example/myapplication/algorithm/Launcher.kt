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
//    BJ1065().main(br.readLine().toInt())
//    BJ2231().main(br.readLine().toInt())
//    val (x,y) = br.readLine().split(" ").map { it.toInt() }
//    val inputList = mutableListOf<String>()
//    repeat(x) {
//        inputList.add(br.readLine())
//    }
//    BJ1018().main(inputList)

//    BJ1436().main(br.readLine().toInt())
    BJ1436().improveMain(br.readLine().toInt())
}