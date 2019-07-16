package com.n26.bitcointracker.settings

import com.n26.bitcointracker.models.Range

open class UserSettingsManager {

    fun saveLastRange(range: Range) {

    }

    open fun getLastRange(): Range? = Range.ALL

}