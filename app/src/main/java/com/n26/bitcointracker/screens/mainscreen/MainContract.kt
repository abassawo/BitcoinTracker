package com.n26.bitcointracker.screens.mainscreen

import com.n26.bitcointracker.base.BaseContract
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.models.Value

interface MainContract {

    interface View : BaseContract.View {
        fun showNoInternetWarning()

        fun showChartPage(rangeIndex: Int)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onRangeSelected(rangeIndex: Int)
        fun onConnectivityChecked(isNetworkAvailable: Boolean)
    }
}