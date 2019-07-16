package com.n26.bitcointracker.screens.mainscreen

import com.n26.bitcointracker.base.BaseMvpActivity
import com.n26.bitcointracker.base.BasePresenter
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.settings.UserSettingsManager
import com.n26.bitcointracker.utils.connectivity.ConnectivityUtil
import javax.inject.Inject

class MainPresenter @Inject constructor(appRepository: AppRepository) : BasePresenter<MainContract.View>(appRepository),
    MainContract.Presenter {

    private var lastSelectedRange: Range? = null

    init {
        if (lastSelectedRange == null) {
            lastSelectedRange = UserSettingsManager.getLastRange() ?: Range.ALL
        }
    }

    override fun onViewBound() {
        super.onViewBound()
//        val isNetworkAvailable = ConnectivityUtil.isNetworkAvailable(view as BaseMvpActivity<*>)
        val isNetworkAvailable = true
        onConnectivityChecked(isNetworkAvailable)
    }

    override fun onConnectivityChecked(isNetworkAvailable: Boolean) {
        if (isNetworkAvailable) {
            view?.showChartPage(Range.values().indexOf(lastSelectedRange))
        } else {
            view?.showNoInternetWarning()
        }
    }
}