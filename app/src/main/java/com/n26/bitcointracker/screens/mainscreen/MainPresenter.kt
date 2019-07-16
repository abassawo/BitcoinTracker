package com.n26.bitcointracker.screens.mainscreen

import com.n26.bitcointracker.base.BaseMvpActivity
import com.n26.bitcointracker.base.BasePresenter
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.settings.UserSettingsManager
import com.n26.bitcointracker.utils.connectivity.ConnectivityUtil
import javax.inject.Inject

class MainPresenter @Inject constructor(appRepository: AppRepository) :
    BasePresenter<MainContract.View>(appRepository),
    MainContract.Presenter {

    override fun onViewBound() {
        super.onViewBound()
        view?.let {
            val isNetworkAvailable = it.isNetworkAvailable()
            onConnectivityChecked(isNetworkAvailable)
        }
    }

    override fun onConnectivityChecked(isNetworkAvailable: Boolean) {
        if (isNetworkAvailable) {
            val lastSelectedRange =appRepository.getLastRange()
            view?.showChartPage(Range.values().indexOf(lastSelectedRange))
        } else {
            view?.showNoInternetWarning()
        }
    }
}