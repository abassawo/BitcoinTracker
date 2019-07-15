package com.n26.bitcointracker.screens.mainscreen

import com.n26.bitcointracker.base.BaseContract
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.models.Value

interface MainContract {

    interface View : BaseContract.View {
        fun showNoInternetWarning()

        fun showChartPage(range: Range)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onRangeSelected(range: Range)
        fun onConnectivityChecked(isNetworkAvailable: Boolean)
    }
}