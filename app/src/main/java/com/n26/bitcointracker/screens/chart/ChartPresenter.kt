//package com.n26.bitcointracker.screens.chart
////
////import com.n26.bitcointracker.base.BasePresenter
////import com.n26.bitcointracker.models.ChartResponse
////import com.n26.bitcointracker.models.Range
////import com.n26.bitcointracker.models.getTimeSpanQueryText
////import com.n26.bitcointracker.rest.AppRepository
////import com.n26.bitcointracker.settings.UserSettings
////import timber.log.Timber
////import javax.inject.Inject
////
////class ChartPresenter @Inject constructor(
////    userSettings: UserSettings,
////    appRepository: AppRepository
////) : BasePresenter<ChartContract.View>(userSettings, appRepository),
////    ChartContract.Presenter {
////
////    override fun onTimeSpanSelected(range: Range) {
////        disposables.clear()
////        view?.toggleChartVisibility(false)
////
////        val disposable = appRepository.getChart(range.getTimeSpanQueryText())
////            .subscribe(
////                { response -> showResponse(response, range) },
////                { e -> showError(e) })
////
////        disposables.add(disposable)
////    }
////
////    private fun showResponse(response: ChartResponse?, range: Range) {
////        response?.let {
////            view?.toggleChartVisibility(true)
////            view?.showChartData(response.values, range)
////        }
////    }
////
////    private fun showError(throwable: Throwable) {
////        Timber.e(throwable)
////        view?.showChartLoadingError()
////    }
////}