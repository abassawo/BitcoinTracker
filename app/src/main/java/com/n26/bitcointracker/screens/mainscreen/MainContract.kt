package com.n26.bitcointracker.screens.mainscreen

import com.n26.bitcointracker.base.BaseContract

interface MainContract {

    interface View : BaseContract.View {
        fun showChartPage(rangeIndex: Int)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun onPageSelected(index: Int)
    }
}