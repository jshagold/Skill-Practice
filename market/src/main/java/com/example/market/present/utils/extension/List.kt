package com.example.market.present.utils.extension

fun <T> MutableList<T>.move(from: Int, to: Int) {
    if(from == to) return

    val element = this.removeAt(from)
    this.add(to, element)
}