package com.example.myapplication.algorithm

import java.io.BufferedWriter
import java.io.OutputStreamWriter

// https://www.acmicpc.net/problem/7568 - 덩치
class BJ7568 {

    fun main(list: List<Pair<Int,Int>>) {
        val ansList = IntArray(list.size) {1}

        list.forEachIndexed { index, pair ->
            for(i in index+1..list.lastIndex) {
                when(comparePair(pair, list[i])) {
                    1 -> {
                        ansList[i]+=1
                    }
                    -1 -> {
                        ansList[index]+=1
                    }
                }
            }
        }

        val bw = BufferedWriter(OutputStreamWriter(System.out))

        ansList.forEach {
            bw.write("$it ")
        }
        bw.flush()
        bw.close()
    }

    private fun comparePair(x: Pair<Int, Int>, y: Pair<Int, Int>): Int {
        return if(x.first > y.first && x.second > y.second) {
            1
        } else if(x.first < y.first && x.second < y.second) {
            -1
        } else {
            0
        }
    }

}