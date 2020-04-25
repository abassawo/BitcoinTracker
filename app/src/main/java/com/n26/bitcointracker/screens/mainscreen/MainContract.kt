package com.n26.bitcointracker.screens.mainscreen

import com.n26.bitcointracker.base.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun showNoInternetWarning()
        fun showChartPage(rangeIndex: Int)
        fun isNetworkAvailable(): Boolean
    }

    interface Presenter {
        fun onPageSelected(index: Int)
        fun onConnectivityChecked(isNetworkAvailable: Boolean)
    }
}
