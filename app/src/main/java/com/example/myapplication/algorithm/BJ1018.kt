package com.example.myapplication.algorithm

import java.io.BufferedWriter
import java.io.OutputStreamWriter

// https://www.acmicpc.net/problem/1018 - 체스판 다시 칠하기
class BJ1018 {
    fun main(inputBoard: List<String>) {
        val bw = BufferedWriter(OutputStreamWriter(System.out))
        var answer = Int.MAX_VALUE
        val board = inputBoard.map { row->
            row.map { element ->
                element != 'B'
            }
        }

        for(i in 0.rangeTo(board.size - 8)) {
            for(j in 0.rangeTo(board[0].size - 8)) {

                val splitBoard = board.subList(i, i+8).map { row ->
                    row.subList(j, j+8)
                }
                val count = checkCount(splitBoard)
                if(count < answer) answer = count
            }
        }

        bw.write("$answer")

        bw.flush()
        bw.close()
    }

    private fun checkCount(board: List<List<Boolean>>): Int {
        var differCount = 0

        for(i in board.indices) {
            for(j in board[0].indices) {
                val sum = i+j
                if((sum % 2 == 0) && board[i][j]) {
                    differCount++
                }
                else if((sum % 2 != 0) && !board[i][j]) {
                    differCount++
                }
            }
        }

        if(differCount > 32) differCount = 64-differCount

        return differCount
    }

}