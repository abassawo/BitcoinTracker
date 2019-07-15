package com.n26.bitcointracker.screens.chart

import android.util.Log
import com.n26.bitcointracker.base.BasePresenter
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.settings.UserSettingsManager
import javax.inject.Inject

class ChartPresenter @Inject constructor() : BasePresenter<ChartContract.View>(),
    ChartContract.Presenter {

    override fun onRangeSelected(range: String) {
        view?.clearChart()
        disposables.clear()

        val disposable = restApi.getChart(range)
            .compose(subscribeOnIoObserveOnUi())
            .subscribe(
                { response -> response.values?.let { view?.showChartData(it) } },
                { e -> e?.message?.let { Log.d(TAG, it) } })

        disposables.add(disposable)
    }

    private val TAG: String? = "MainPresenter"

    override fun onViewBound() {
        onRangeSelected(Range.ALL.timeSpan)
    }

    private fun getLastRange() = UserSettingsManager.getLastRange() ?: Range.ALL
}