package com.example.myapplication.algorithm

import java.io.BufferedWriter
import java.io.OutputStreamWriter

// https://www.acmicpc.net/problem/2309 - 일곱 난쟁이
class BJ2309 {
    private val pick = mutableListOf<Int>()

    fun main(list: List<Int>) {
        combination(7,0,list)

        val bw = BufferedWriter(OutputStreamWriter(System.out))
        pick.sorted().forEach {
            bw.write("$it\n")
        }
        bw.flush()
        bw.close()
    }

    private fun combination(count: Int, curIndex: Int, list: List<Int>): Boolean {
        if(count == 0) {
            return pick.sum() == 100
        }

        for(i in curIndex..8) {
            pick.add(list[i])
            if(combination(count-1,i+1,list)) {
                return true
            }
            pick.removeLast()
        }

        return false
    }
}