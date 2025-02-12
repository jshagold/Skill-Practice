package com.example.myapplication.algorithm

// https://www.acmicpc.net/problem/2231 - 분해합
class BJ2231 {


    fun main(n: Int) {
        var answer = Int.MAX_VALUE
        for(i in n.downTo(1)) {
            if(decompositionSum(i) == n && i <= answer) {
                answer = i
            }
        }

        if(answer == Int.MAX_VALUE) answer = 0

        println(answer)
    }

    private fun decompositionSum(n: Int): Int {
        return n + n.toString().sumOf { it.digitToInt() }
    }

}