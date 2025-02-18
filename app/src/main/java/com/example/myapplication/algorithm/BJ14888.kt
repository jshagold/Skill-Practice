package com.example.myapplication.algorithm

import java.io.BufferedWriter
import java.io.OutputStreamWriter

class BJ14888 {
    private var numberList: List<Int>? = null
    private val pick = mutableListOf<String>()
    private var visited: BooleanArray? = null
    private var max = Int.MIN_VALUE
    private var min = Int.MAX_VALUE

    fun main(numList: List<Int>, signCount: List<Int>) {
        val plus = signCount[0]
        val minus = signCount[1]
        val multiple = signCount[2]
        val count = numList.size - 1
        numberList = numList
        val list = List(count) {
            when (it) {
                in 0 until plus -> "+"
                in plus until plus+minus -> "-"
                in plus+minus until plus+minus+multiple -> "x"
                else -> "/"
            }
        }
        visited = BooleanArray(count) {true}

        perm(list, count)

        val bw = BufferedWriter(OutputStreamWriter(System.out))

        bw.write("$max\n$min")
        bw.flush()
        bw.close()
    }

    private fun perm(list: List<String>, count: Int) {
        if(count == 0) {
            val number = calcul(pick)

            if(number > max) max = number
            if(number < min) min = number

            return
        }

        for(i in list.indices) {
            if(visited!![i]) {
                visited!![i] = false
                pick.add(list[i])
                perm(list, count-1)
                pick.removeLast()
                visited!![i] = true
            }
        }
    }

    private fun calcul(signList: List<String>): Int {
        var calNum = numberList!![0]
        signList.forEachIndexed { index, sign ->
            when(sign) {
                "+" -> { calNum += numberList!![index+1] }
                "-" -> { calNum -= numberList!![index+1] }
                "x" -> { calNum *= numberList!![index+1] }
                else -> { calNum /= numberList!![index+1] }
            }
        }

        return calNum
    }

}