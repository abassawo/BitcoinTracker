package com.n26.bitcointracker.screens.chart

import androidx.lifecycle.MutableLiveData
import com.n26.bitcointracker.base.BaseViewModel
import com.n26.bitcointracker.di.AppSchedulerProvider
import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.models.getTimeSpanQueryText
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.rest.RetrofitClient
import timber.log.Timber

class ChartViewModel : BaseViewModel() {

    private val appRepository = AppRepository(AppSchedulerProvider(), RetrofitClient.getService())
    val viewState: MutableLiveData<ChartViewState> = MutableLiveData()

    init {
        onTimeSpanSelected(range = Range.ALL)
    }

    fun onTimeSpanSelected(range: Range) {
        disposables.clear()
        viewState.postValue(ChartViewState.Loading)

        val disposable = appRepository.getChart(range.getTimeSpanQueryText())
            .subscribe(
                { response -> showResponse(response, range) },
                { e -> showError(e) })

        disposables.add(disposable)
    }

    private fun showResponse(response: ChartResponse?, range: Range) =
        response?.let {
            viewState.postValue(ChartViewState.Content(response, range))
        }

    private fun showError(throwable: Throwable) {
        Timber.e(throwable)
        viewState.postValue(ChartViewState.Error(throwable))
    }
}
