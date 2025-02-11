package com.example.myapplication.algorithm

// https://www.acmicpc.net/problem/1065 - 한수
class BJ1065 {

    fun main(num: Int) {

        var count = 0
        for(i in 1..num) {
           if(checkAP(i)) count++
        }

        println(count)
    }


    fun checkAP(n: Int): Boolean {
        if(n.toString().length <= 2) {
            return true
        }

        val numString = n.toString()
        val differ = numString[0] - numString[1]
        var answer = true

        for(i in 1 until numString.length-1) {
            if(differ != (numString[i] - numString[i+1])) {
                answer = false
                break
            }
        }

        return answer
    }
}