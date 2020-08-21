package com.n26.bitcointracker.screens.chart

import androidx.lifecycle.MutableLiveData
import com.n26.bitcointracker.base.BaseViewModel
import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.models.getTimeSpanQueryText
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.rest.RetrofitClient
import kotlinx.coroutines.runBlocking
import timber.log.Timber

class ChartViewModel : BaseViewModel() {

    private val appRepository = AppRepository(RetrofitClient.getService())
    val viewState: MutableLiveData<ChartViewState> = MutableLiveData()

    init {
        onTimeSpanSelected(range = Range.ALL)
    }

    fun onTimeSpanSelected(range: Range) {
        disposables.clear()
        viewState.postValue(ChartViewState.Loading)
        fetchData(range)
    }

    private fun fetchData(range: Range) {
        runBlocking {
            try {
                getData(range)
            } catch (e: Exception) {
                showError(e)
            }
        }
    }

    private suspend fun getData(range: Range) {
        val chartResponse = appRepository.getChartBlocking(range.getTimeSpanQueryText())
        showResponse(chartResponse, range)
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
