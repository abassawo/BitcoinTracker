package com.n26.bitcointracker.screens.chart

import com.n26.bitcointracker.base.BaseContract
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.models.Value

interface ChartContract {

    interface View : BaseContract.View {
        fun showChartData(values: List<Value>)
        fun clearChart()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onRangeSelected(range: String)
    }
}