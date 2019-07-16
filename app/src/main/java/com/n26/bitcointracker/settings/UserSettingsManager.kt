package com.n26.bitcointracker.settings

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager.getDefaultSharedPreferences
import com.n26.bitcointracker.models.Range

open class UserSettingsManager(context: Context) : UserSettings {
    val preferences: SharedPreferences = getDefaultSharedPreferences(context)

    override fun saveLastTimeSpanRange(range: Range) {
        preferences.edit().putInt(LAST_RANGE_VIEWED, range.ordinal).apply()
    }

    override fun getLastTimeSpanRange(): Range? =
        Range.values()[preferences.getInt(LAST_RANGE_VIEWED, 0)]

    companion object {
        val LAST_RANGE_VIEWED = "lastRangeViewed"
    }
}