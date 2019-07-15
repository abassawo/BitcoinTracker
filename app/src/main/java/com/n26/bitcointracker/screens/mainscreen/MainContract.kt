package com.n26.bitcointracker.screens.mainscreen

import com.n26.bitcointracker.base.BaseContract
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.models.Value

interface MainContract {

    interface View : BaseContract.View {
        fun showChartData(values: List<Value>, range: Range)
        fun clearChart()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onRangeSelected(range: Range)
    }
}