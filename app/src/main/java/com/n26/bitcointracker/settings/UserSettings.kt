package com.n26.bitcointracker.settings

import com.n26.bitcointracker.models.Range

interface UserSettings {

    fun saveLastTimeSpanRange(range: Range)

    fun getLastTimeSpanRange(): Range?
}