package com.example.myapplication.algorithm

class BJ2501 {

    fun main(n: Int, k: Int) {
        var index = 0
        var answer = 0
        for(i in 1..n) {
            if(n%i == 0) {
                index++
                if(index == k) {
                    answer = i
                    break
                }
            }
        }

        println(answer)
    }
}