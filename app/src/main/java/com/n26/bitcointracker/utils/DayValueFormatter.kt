package com.n26.bitcointracker.utils

import com.github.mikephil.charting.formatter.ValueFormatter

class DayValueFormatter : ValueFormatter() {

    override fun getFormattedValue(value: Float): String {
        if(value.toInt() == 0) return value.toInt().toString()
        return """${value.toInt()}days"""
    }
}