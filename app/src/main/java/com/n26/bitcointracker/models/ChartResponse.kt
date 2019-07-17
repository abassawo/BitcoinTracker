package com.n26.bitcointracker.models

import androidx.annotation.StringRes
import com.n26.bitcointracker.R

class ChartResponse {
    private var unit: String? = null

    private var period: String? = null

    var values: List<Value>? = null

    private var name: String? = null

    private var description: String? = null

    private var status: String? = null

    override fun toString(): String {
        return "ChartResponse [unit = $unit, period = $period, values = $values, name = $name, description = $description, status = $status]"
    }
}

enum class Range(val timeSpan: String, @StringRes val shortLabel: Int) {
    THIRTY_DAYS("30 days", R.string.thirty_days),
    SIXTY_DAYS("60 days", R.string.sixty_days),
    HUNDRED_AND_EIGHTY_DAYS("180 days", R.string.oneEighty_days),
    ONE_YEAR("1 year", R.string.one_year),
    TWO_YEARS("2 years", R.string.two_years),
    ALL("all", R.string.all)
}

//Extension function used to support valid queries - ex: 30 days -> 30days
fun Range.getTimeSpanQueryText() : String = timeSpan.replace(" ", "").trim()


class Value {
    private var x: String? = null

    private var y: String? = null

    fun getYAsFloat() = y?.toFloat() ?: 0f

    override fun toString(): String {
        return "Value [x = $x, y = $y]"
    }
}
