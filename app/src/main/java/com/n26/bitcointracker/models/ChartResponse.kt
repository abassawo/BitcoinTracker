package com.n26.bitcointracker.models

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