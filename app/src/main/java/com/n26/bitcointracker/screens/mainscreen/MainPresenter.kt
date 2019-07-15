package com.n26.bitcointracker.screens.mainscreen

import com.n26.bitcointracker.base.BaseMvpActivity
import com.n26.bitcointracker.base.BasePresenter
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.settings.UserSettingsManager
import com.n26.bitcointracker.utils.connectivity.ConnectivityUtil
import javax.inject.Inject

class MainPresenter @Inject constructor() : BasePresenter<MainContract.View>(),
    MainContract.Presenter {
    override fun onRangeSelected(range: Int) {
        view?.showChartPage(range)
    }

    private var selectedRange: Range? = null

    init {
        if (selectedRange == null) {
            selectedRange = UserSettingsManager.getLastRange() ?: Range.ALL
        }
    }

    override fun onViewBound() {
        super.onViewBound()
        val isNetworkAvailable = ConnectivityUtil.isNetworkAvailable(view as BaseMvpActivity<*>)
        onConnectivityChecked(isNetworkAvailable)
    }

    override fun onConnectivityChecked(isNetworkAvailable: Boolean) {
        if (isNetworkAvailable) {
            onRangeSelected(Range.values().size - 1)
        } else {
            view?.showNoInternetWarning()
        }
    }
}