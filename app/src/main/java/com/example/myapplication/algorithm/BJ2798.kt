package com.example.myapplication.algorithm

// https://www.acmicpc.net/problem/2798 - 블랙잭
class BJ2798(private val targetNum: Int, private val cardList: List<Int>) {
    private var sum = 0
    private var answer = 0
    private var visited: BooleanArray = BooleanArray(cardList.size) {false}


    fun main(): Int {

        combination(3,0)

        return answer
    }

    private fun combination(count: Int, curIndex: Int) {
        if(count == 0) {
            if(answer < sum && sum <= targetNum) {
                answer = sum
            }
            return
        }

        for(i in curIndex..cardList.lastIndex) {
            if(!visited[i]) {
                visited[i] = true
                sum += cardList[i]
                combination(count-1, i+1)
                visited[i] = false
                sum -= cardList[i]
            }
        }
    }


}