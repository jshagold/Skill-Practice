package com.example.myapplication.algorithm

// https://www.acmicpc.net/problem/1436 - 영화감독 숌
class BJ1436 {
    val numList = List(10) { it }
    val pick = mutableListOf<Int>()
    val resultList = mutableListOf(666)


    fun main(numIndex: Int) {

        permutation(1)
        if(numIndex > 19) {
            permutation(2)
        }
        if(numIndex > 280) {
            permutation(3)
        }
        if(numIndex > 3700) {
            permutation(4)
        }

        println(resultList.distinct().sorted()[numIndex-1])
    }


    private fun permutation(count: Int) {
        if(count == 0) {
            for(i in 0..pick.lastIndex) {
                pick.add(i, 666)
                pick.joinToString { it.toString() }
                resultList.add(pick.joinToString("").toInt())
                pick.removeAt(i)
            }
            pick.add(666)
            pick.joinToString { it.toString() }
            resultList.add(pick.joinToString("").toInt())
            pick.removeLast()
            return
        }

        for(i in 0..numList.lastIndex) {
            pick.add(numList[i])
            permutation(count-1)
            pick.removeLast()
        }
    }


    fun improveMain(numIndex: Int) {

        var answer = 666
        var i = 0

        while(true) {
            if(isThreeSix(answer)) {
                i+=1
            }
            if(i == numIndex) break
            answer++
        }

        println(answer)
    }

    private fun isThreeSix(n: Int): Boolean {
        var stack = 0
        var num = n
        var ans = false
        while(num != 0) {
            if(num % 10 == 6) {
                stack++
            } else {
                stack = 0
            }
            if(stack >= 3) {
                ans = true
                break
            }
            num /= 10
        }


        return ans


        // 개선 이전
//        return n.toString().contains("666")
    }

}