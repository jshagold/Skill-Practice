package com.example.domain

import java.lang.Math.pow
import java.util.LinkedList
import java.util.Queue
import java.util.Stack
import kotlin.math.max
import kotlin.math.pow
import kotlin.math.sqrt

class MyClass {

    fun test(word1: String, word2: String){

        val tt = word1.toList()

        val words = "asdf"
        val word = "as"

        val a = (0..words.length)
        val sb = StringBuilder()


        words.reversed().forEach {  }

        sb.reversed().toString()


        val b= sb.substring(0,1)
        words.equals("aaa")


        val wordList = words.split(' ')
        val cc = wordList.filter {
            it.isNotBlank()
        }


        tt.reversed()


        val candies = arrayOf(2,3,5,1,3)

        val can = candies.mapIndexed { index, i ->
            index
        }.toIntArray()

        a.forEach breaker@ {
            return@breaker
        }

        val aa = Array(3) { 0 }

        val aal = List(3) {0}
        aal.toIntArray()

        val aaat: Array<IntArray> = arrayOf()
        aaat.asSequence().sortedBy {
            it[0]
        }.toList()

        aa.sortedArray()

        aa[0] = 1

        for(i in 0..10) {

        }

        val aaa = 10
        if(aaa in 11..100) {

        }

        aaa.toDouble().pow(2)

        words.contains(word)
        words.isEmpty()

        val alpha = 'a'

        val dd = edges[0][0]

        val bc = visited.find { !it }

        sqrt(10.0)
        val n = 10

        Int.MIN_VALUE

        max(1,2)

        val r = 0..n
        val r2 = (n-5).rangeTo(n-1)
    }

    fun list() {
        val n = 10
        val route: MutableList<MutableList<Int>> = MutableList(10) { mutableListOf() }
        val candies = intArrayOf(2,3,5,1,3)
        val can = candies.sumOf { it.toLong() }

        val array = IntArray(3) {0}

        candies.last()

        val list:MutableList<Obj> = MutableList(10) { Obj(0,0,0) }

        list.slice(0..3)
        list.sortedByDescending {
            it.a
        }

        list.sortedWith(compareBy({it.a}, {it.b}, {it.c}))
    }

    data class Obj(
        val a: Int,
        val b: Int,
        val c: Int
    )

    fun collections() {
        val nums1 = intArrayOf(2,3,5,1,3)

        for(i in nums1.lastIndex downTo 0 step 1) {

        }

        val a = nums1.indexOf(0)

        nums1.toSet()
        val set1: Set<Int> = setOf()
        val set2: Set<Int> = setOf()
        set1.minus(set2)
        val set3 = mutableSetOf<Int>()
        set3.add(1)
        set3.remove(1)


        val map = mutableMapOf<Int, Int>()
        map[2] = 2
        map.containsKey(1)
        map.getOrDefault(1, 200) //200

        val aaa = map.filterValues {
            it == 1
        }


        if(map[2]!! > 0) {

        }

        val v = map.values.toIntArray()
        val k = map.keys.sorted()

        run {
            map.forEach aaa@ {
                return@run
            }
        }



        val b = nums1.asSequence().mapIndexed { mapindex, it ->
            if(it == 0){
                0
            } else {
                nums1.reduceIndexed { index, acc, i ->
                    if(index != mapindex) {
                        acc*i
                    } else {
                        acc
                    }
                }
            }
        }.toList().toIntArray()

        nums1.foldIndexed(1) {index, acc, i ->
            if(index != 0) {
                acc*i
            } else {
                acc
            }
        }

        val list: List<Pair<Int, Int>> = listOf()

        list.sortedByDescending {
            it.first
        }

        list.find {
            it.first == 0
        }

        val t = nums1.toMutableList()
    }

    fun stack() {
        val stack: Stack<Int> = Stack()
        stack.empty()




        val a = -1
        val b = -2
        val c = 3
    }

    fun queue() {
        val a = intArrayOf(0,1,2,3)
        val list = a.toList()
        a.toMutableList()

        val q: Queue<Int> = LinkedList()
        q.sum()
        q.size
    }

    val visited: MutableList<Boolean> = MutableList(3) {false}
    val edges: List<List<Int>> = listOf()

    fun dfs(index: Int) {
        visited[index] = true

        for(next in edges[index]) {
            if(!visited[index]) {
                dfs(next)
            }
        }

        Int.MAX_VALUE
    }


    val queue: Queue<Int> = LinkedList()
    val visitedBFS: MutableList<Boolean> = MutableList(3) { false }
    fun bfs(root: Int) {
        queue.add(root)
        visitedBFS[root] = true

        while(queue.isNotEmpty()) {
            val head = queue.poll()

            for(next in edges[head]) {
               queue.add(next)
               visitedBFS[next] = true
            }
        }
    }

    fun binarySearch(numbers: IntArray, target: Int): Int {
        val answer = -1

        var low = 0
        var high = numbers.lastIndex

        while(low < high) {
            val mid = (low + high) / 2

            if(numbers[mid] < target) {
                low = mid + 1
            } else if(numbers[mid] > target) {
                high = mid - 1
            } else {
                return mid
            }
        }

        return answer
    }

    fun successfulPairs(spells: IntArray, potions: IntArray, success: Long): IntArray {

        val result = spells.asSequence().map { spell ->
            potions.count { potion ->
                spell.toLong() * potion.toLong() >= success
            }.toInt()
        }.toList().toIntArray()

        return result
    }


}