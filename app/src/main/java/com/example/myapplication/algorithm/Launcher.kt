package com.example.myapplication.algorithm

import java.io.BufferedReader
import java.io.InputStreamReader

fun main(args: Array<String>) {
    val br = BufferedReader(InputStreamReader(System.`in`))

    br.readLine()
    val numList = br.readLine().split(" ").map { it.toInt() }
    val signCount = br.readLine().split(" ").map { it.toInt() }
    BJ14888().main(numList, signCount)
}


fun solve1() {
//    val br = BufferedReader(InputStreamReader(System.`in`))

//    val (x, target) = readln().split(" ").map { it.toInt() }
//    val cardList = readln().split(" ").map { it.toInt() }
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

//    BJ1436().improveMain(br.readLine().toInt())

//    val people = br.readLine().toInt()
//    val list = mutableListOf<Pair<Int, Int>>()
//    repeat(people) {
//        val pair = br.readLine().split(" ")
//        list.add(Pair(pair[0].toInt(), pair[1].toInt()))
//    }
//    BJ7568().main(list)

//    val list = mutableListOf<Int>()
//    repeat(9) {
//        list.add(br.readLine().toInt())
//    }
//    BJ2309().main(list)

    // todo 다시풀어
//    BJ9663().main(br.readLine().toInt())

//    val pairList = mutableListOf<Pair<Int, Int>>()
//    repeat(br.readLine().toInt()) {
//        val input = br.readLine().split(" ").map{ it.toInt() }
//        pairList.add(Pair(input[0], input[1]))
//    }
//    BJ14501().main(pairList)

//    val(x,y) = br.readLine().split(" ").map { it.toInt() }
//    BJ2501().main(x, y)
}