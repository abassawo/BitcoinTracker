package com.n26.bitcointracker.screens.mainscreen

import com.n26.bitcointracker.base.BasePresenter
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.settings.UserSettings
import javax.inject.Inject

class MainPresenter @Inject constructor(settings: UserSettings, appRepository: AppRepository) :
    BasePresenter<MainContract.View>(settings, appRepository),
    MainContract.Presenter {

    override fun onPageSelected(index: Int) =
        userSettings.saveLastTimeSpanRange(Range.values()[index])

    override fun onViewBound() {
        super.onViewBound()
        view?.let {
            val lastSelectedRange = userSettings.getLastTimeSpanRange()
            view?.showChartPage(Range.values().indexOf(lastSelectedRange))
        }
    }
}