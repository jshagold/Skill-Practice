package com.example.myapplication.algorithm

class Permutation {
    /**
     * 순열
     */
    val numList = listOf(1,2,3,4,5)
    val visited = BooleanArray(numList.size) {false}
    val pick = mutableListOf<Int>()
    var permutationCount: Int = 0

    // list에서 count개수만큼 element뽑아내기
    fun permutation(count: Int) {
        if(count == 0) {
            println(pick)
            permutationCount++
            return
        }

        for(i in 0..numList.lastIndex) {
            if(!visited[i]) {
                visited[i] = true
                pick.add(numList[i])
                permutation(count-1)
                pick.remove(numList[i])
                visited[i] = false
            }
        }
    }

    /**
     * 중복 순열
     */
    val dPPick = mutableListOf<Int>()
    var dPCount = 0

    fun duplicatePermutation(count: Int) {
        if(count == 0) {
            println(dPPick)
            dPCount++
            return
        }

        for(i in 0..numList.lastIndex) {
            dPPick.add(numList[i])
            duplicatePermutation(count-1)
            dPPick.remove(numList[i])
        }
    }

    /**
     * 조합
     */
    val cPick = mutableListOf<Int>()
    var cCount = 0

    fun combination(curIndex: Int, count: Int) {
        if(count == 0) {
            cCount++
            println(cPick)
            return
        }

        for(i in curIndex..numList.lastIndex) {
            cPick.add(numList[i])
            combination(i+1, count-1)
            cPick.remove(numList[i])
        }
    }

    /**
     * 중복 조합
     */
    val dCPick = mutableListOf<Int>()
    var dCCount = 0

    fun duplicateCombination(curIndex: Int, count: Int) {
        if(count == 0) {
            dCCount++
            println(dCPick)
            return
        }

        for(i in curIndex..numList.lastIndex) {
            dCPick.add(numList[i])
            duplicateCombination(i,count-1)
            dCPick.remove(numList[i])
        }
    }
}

fun permTest() {
    val perm = Permutation()
    perm.permutation(2)
    println("permutation count: ${perm.permutationCount}")
    perm.duplicatePermutation(2)
    println("duplication permutation count: ${perm.dPCount}")
    perm.combination(0,2)
    println("combination count: ${perm.cCount}")
    perm.duplicateCombination(0,2)
    println("duplication combination count: ${perm.dCCount}")
}