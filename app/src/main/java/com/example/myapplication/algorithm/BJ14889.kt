package com.example.myapplication.algorithm

import android.util.Log.i
import com.google.common.collect.Multimaps.index
import kotlin.math.abs

// https://www.acmicpc.net/problem/14889 - 스타트와 링크

// 1. N개 중에 N/2개 뽑기 (중복없이)
// 2. N/2개에서 Sij 총합 구해서 비교
class BJ14889 {
    var board: List<List<Int>> = listOf()
    var list: List<Int> = listOf()
    var answer = Int.MAX_VALUE
    var visited: BooleanArray? = null


    fun main(inputBoard: List<List<Int>>) {
        board = inputBoard
        list = List(inputBoard.size) { it }
        visited = BooleanArray(inputBoard.size)

        combination(0, inputBoard.size/2)

        println(answer)
    }

    fun combination(curIndex: Int, count: Int) {
        if(count == 0) {

            calculate()
            return
        }

        for(i in curIndex..list.lastIndex) {
            if(!visited!![i]) {
                visited!![i] = true
                combination(i+1, count-1)
                visited!![i] = false
            }
        }
    }

    fun calculate() {
        var sum1 = 0
        var sum2 = 0

        visited!!.indices.forEach { i ->
            for(j in i+1..visited!!.lastIndex) {
                if(visited!![i] && visited!![j]) {
                    sum1 += board[i][j] + board[j][i]
                } else if(!visited!![i] && !visited!![j]) {
                    sum2 += board[i][j] + board[j][i]
                }
            }
        }

        if(answer > abs(sum1-sum2)) answer = abs(sum1-sum2)
    }
}