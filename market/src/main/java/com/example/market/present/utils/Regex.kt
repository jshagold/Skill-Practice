package com.example.market.present.utils

fun stringToFloat(input: String): Float {
    val regex = Regex("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)")
    val match = regex.find(input)

    return match?.value?.toFloatOrNull()?: 0f
}


fun checkStringToFloat(input: String): Boolean {
    val regex = Regex("[+-]?([0-9]+([.][0-9]*)?|[.][0-9]+)")

    return input.matches(regex)
}