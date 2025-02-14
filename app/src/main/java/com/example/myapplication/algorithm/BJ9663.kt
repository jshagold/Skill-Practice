package com.example.myapplication.algorithm

class BJ9663 {
    private var answer = 0
    // todo 다시풀어
    fun main(n: Int) {
        val visited = List(n) { IntArray(n) {0} }


        combination(n, visited, n, Pair(0,0), 1)
        println(answer)


    }


    private fun combination(size: Int, board: List<IntArray>, count: Int, curIndex: Pair<Int,Int>, depth: Int) {



        if(count == 0) {
            answer++
            return
        }

        for(col in curIndex.first until size) {
            for(row in curIndex.second until size) {

                if(board[col][row] == 0) {

                    closeBoard(size, board, Pair(col, row), depth)

                    val nextCol = col+1
                    val nextRow = 0
                    combination(size, board, count-1, Pair(nextCol, nextRow), depth+1)

                    clearAfterDepth(board, depth)

                }
            }
        }

    }


    private fun closeBoard(size: Int, board: List<IntArray>, coords: Pair<Int,Int>, depth: Int) {

        // 왼쪽
        for(row in 0..coords.second) {
            if(board[coords.first][row] == 0) board[coords.first][row] = depth
        }
        // 오른쪽
        for(row in coords.second until size) {
            if(board[coords.first][row] == 0) board[coords.first][row] = depth
        }
        // 위
        for(col in 0..coords.first) {
            if(board[col][coords.second] == 0) board[col][coords.second] = depth
        }
        // 아래
        for(col in coords.first until size) {
            if(board[col][coords.second] == 0) board[col][coords.second] = depth
        }
        // 왼쪽 하단 대각
        var i = 0
        for(row in coords.second.downTo(0)) {
            if(coords.first+i >= size) {
                break
            }
            if(board[coords.first+i][row] == 0) board[coords.first+i][row] = depth
            i+=1
        }
        // 오른쪽 하단 대각
        i=0
        for(row in coords.second until size) {
            if(coords.first+i >= size) {
                break
            }
            if(board[coords.first+i][row] == 0) board[coords.first+i][row] = depth
            i+=1
        }
    }

    private fun clearAfterDepth(board: List<IntArray>, depth: Int) {
        for(col in board.indices) {
            for(row in board.indices) {
                if(board[col][row] == depth) {
                    board[col][row] = 0
                }
            }
        }
    }
}