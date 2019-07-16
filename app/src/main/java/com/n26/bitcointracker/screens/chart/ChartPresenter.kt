package com.n26.bitcointracker.screens.chart

import com.n26.bitcointracker.base.BasePresenter
import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.settings.UserSettings
import timber.log.Timber
import javax.inject.Inject

class ChartPresenter @Inject constructor(
    userSettings: UserSettings,
    appRepository: AppRepository
) : BasePresenter<ChartContract.View>(userSettings, appRepository),
    ChartContract.Presenter {

    override fun onTimeSpanSelected(range: Range) {
        disposables.clear()
        view?.toggleChartVisibility(false)
        val disposable = appRepository.getChart(range.timeSpan)
            .subscribe(
                { response -> showResponse(response) },
                { e -> showError(e) })

        disposables.add(disposable)
    }

    private fun showResponse(response: ChartResponse?) {
        response?.let {
            view?.toggleChartVisibility(true)
            view?.showChartData(response.values)
        }

    }

    private fun showError(throwable: Throwable) {
        Timber.e(throwable)
        view?.showChartLoadingError()
    }
}