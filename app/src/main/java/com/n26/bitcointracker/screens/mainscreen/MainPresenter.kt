package com.n26.bitcointracker.screens.mainscreen

import android.util.Log
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.base.BasePresenter
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.settings.UserSettingsManager
import javax.inject.Inject

class MainPresenter @Inject constructor() : BasePresenter<MainContract.View>(), MainContract.Presenter {
    var selectedRange: Range? = null

    override fun onRangeSelected(range: Range) {
        if(selectedRange != range) {
            view?.clearChart()
        }
        selectedRange = range

        val disposable = restApi.getChart(range.timeSpan)
            .compose(subscribeOnIoObserveOnUi())
            .subscribe(
                { response -> response.values?.let { view?.showChartData(it, range) } },
                { e -> e?.message?.let { Log.d(TAG, it)  }  })

        disposables.add(disposable)
    }

    private val TAG: String? = "MainPresenter"

    override fun onViewBound() {
        super.onViewCreated()
        BitcoinApp.instance.baseLibComponent?.inject(this)
        onRangeSelected(selectedRange ?: getLastRange())

    }

    private fun getLastRange() = UserSettingsManager.getLastRange() ?: Range.ALL
}