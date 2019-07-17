package com.n26.bitcointracker.utils

import com.github.mikephil.charting.formatter.ValueFormatter
import kotlin.math.roundToInt

class DollarValueFormatter : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        return "$" + value.roundToInt().toString()
    }
}