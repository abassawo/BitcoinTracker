package com.n26.bitcointracker.screens.chart

import android.util.Log
import com.n26.bitcointracker.base.BasePresenter
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.settings.UserSettingsManager
import javax.inject.Inject

class ChartPresenter @Inject constructor(appRepository: AppRepository) : BasePresenter<ChartContract.View>(appRepository),
    ChartContract.Presenter {

    override fun onRangeSelected(range: String) {
        disposables.clear()

        val disposable = appRepository.getChart(range)
            .subscribe(
                { response -> response.values?.let { view?.showChartData(it) } },
                { e -> e?.message?.let { Log.d(TAG, it) } })

        disposables.add(disposable)
    }

    private val TAG: String? = "MainPresenter"

    override fun onViewBound() {
        onRangeSelected(getLastRange().timeSpan)
    }

    private fun getLastRange() = appRepository.getLastRange()
}