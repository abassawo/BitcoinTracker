package com.n26.bitcointracker.screens.chart

import android.app.Application
import com.n26.bitcointracker.base.BaseViewModel
import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.models.getTimeSpanQueryText

class ChartViewModel(val app: Application) : BaseViewModel(app) {

    fun onTimeSpanSelected(range: Range) {
        disposables.clear()

        val disposable = appRepository.getChart(range.getTimeSpanQueryText())
            .subscribe(
                { response -> processResponse(response, range) },
                { e -> sendViewEvent(ChartViewEvent.ErrorCase(e.toString())) })

        disposables.add(disposable)
    }

    private fun processResponse(response: ChartResponse, range: Range ) {
        sendViewEvent(ChartViewEvent.DataLoaded(response, range))
    }
}