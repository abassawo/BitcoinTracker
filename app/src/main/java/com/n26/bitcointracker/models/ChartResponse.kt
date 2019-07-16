package com.n26.bitcointracker.models

import androidx.annotation.StringRes
import com.n26.bitcointracker.R

class ChartResponse {
    var unit: String? = null

    var period: String? = null

    var values: List<Value>? = null

    var name: String? = null

    var description: String? = null

    var status: String? = null

    override fun toString(): String {
        return "ChartResponse [unit = $unit, period = $period, values = $values, name = $name, description = $description, status = $status]"
    }
}

enum class Range(val timeSpan: String, @StringRes val friendlyName: Int) {
    `30_DAYS`("30days", R.string.thirty_days),
    `60_DAY`("60days", R.string.sixty_days),
    `180_DAYS`("180days", R.string.oneEighty_days),
    `1_YEAR`("1year", R.string.one_year),
    `2_YEARS`("2years", R.string.two_years),
    ALL("all", R.string.all)
}

class Value {
    var x: String? = null

    var y: String? = null

    fun getXAsFloat() = x?.toFloat() ?: 0f
    fun getYAsFloat() = y?.toFloat() ?: 0f

    override fun toString(): String {
        return "Value [x = $x, y = $y]"
    }
}
