package com.example.myapplication.algorithm

import java.io.BufferedWriter
import java.io.OutputStreamWriter

// https://www.acmicpc.net/problem/4673 - 셀프 넘버
class BJ4673 {
    private val checkList = BooleanArray(10001) {true}

    fun main() {
        val bw = BufferedWriter(OutputStreamWriter(System.out))

        checkList[0] = false
        for(i in 1 until 10001) {
            if(checkList[i]) {
                var self = selfNum(i)
                while(self <= 10000) {
                    checkList[self] = false
                    self = selfNum(self)
                }
            }
        }

        checkList.forEachIndexed { index, b ->
            if(b) bw.write("$index\n")
        }

        bw.flush()
        bw.close()
    }

    private fun selfNum(num: Int): Int {
        return num + num.toString().sumOf { it.digitToInt() }
    }


}