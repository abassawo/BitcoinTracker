package com.n26.bitcointracker.models

enum class Range(val timeSpan: String) {
    `30_DAYS`("30days"),
    `1_DAY`("60days"),
    `180_DAYS`("180days"),
    `1_YEAR`("1year"),
    `2_YEARS`("2years"),
    ALL("ALL")
}