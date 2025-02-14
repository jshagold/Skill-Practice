package com.example.myapplication.algorithm

class BJ14501 {
    private val pick = mutableListOf<Pair<Int, Int>>()
    private var sum = 0

    fun main(pairList: List<Pair<Int, Int>>) {
        combination(pairList,false,0)
        println(sum)
    }

    private fun combination(list: List<Pair<Int, Int>>, isFinish: Boolean, curIndex: Int) {
        if(isFinish) {
            var pickSum = pick.sumOf { it.second }
            if(curIndex-1 > list.lastIndex) pickSum-=pick.last().second
            if(sum < pickSum) {
                sum = pickSum
            }
            return
        }

        for(i in curIndex..list.lastIndex) {
            pick.add(list[i])
            combination(list, i + list[i].first > list.lastIndex, i + list[i].first)
            pick.removeLast()
        }
    }
}