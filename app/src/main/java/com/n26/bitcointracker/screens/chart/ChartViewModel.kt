package com.n26.bitcointracker.screens.chart

import androidx.lifecycle.MutableLiveData
import com.n26.bitcointracker.base.BaseViewModel
import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.models.timeSpanQueryText
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.rest.RetrofitClient
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class ChartViewModel : BaseViewModel() {

    private val appRepository = AppRepository(RetrofitClient.getService())
    val viewState: MutableLiveData<ChartViewState> = MutableLiveData()

    init {
        onTimeSpanSelected(range = Range.ALL)
    }

    fun onTimeSpanSelected(range: Range) {
        viewState.postValue(ChartViewState.Loading)
        runBlocking { getData(range) }
    }

    @ExperimentalCoroutinesApi
    private suspend fun getData(range: Range) =
        appRepository.getChart(range.timeSpanQueryText())
            .catch { showError(it) }
            .collect { showResponse(it, range) }

    private fun showResponse(response: ChartResponse?, range: Range) =
        response?.let {
            viewState.postValue(ChartViewState.Content(response, range))
        }

    private fun showError(throwable: Throwable) {
        Timber.e(throwable)
        viewState.postValue(ChartViewState.Error(throwable))
    }
}
