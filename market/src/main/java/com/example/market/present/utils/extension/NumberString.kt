package com.example.market.present.utils.extension

import android.icu.text.DecimalFormat

fun Int.toPriceFormat(): String {
    val decimalFormat = DecimalFormat("#,###")

    return decimalFormat.format(this)
}