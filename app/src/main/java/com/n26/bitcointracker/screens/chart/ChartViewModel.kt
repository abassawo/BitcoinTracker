package com.n26.bitcointracker.screens.chart

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.n26.bitcointracker.base.BaseViewModel
import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.models.timeSpanQueryText
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.rest.RetrofitClient
import kotlinx.coroutines.launch
import timber.log.Timber

class ChartViewModel : BaseViewModel() {

    private val appRepository = AppRepository(RetrofitClient.getService())
    val viewState: MutableLiveData<ChartViewState> = MutableLiveData()

    fun onTimeSpanSelected(range: Range) {
        viewState.postValue(ChartViewState.Loading)

        viewModelScope.launch {
            runCatching { getData(range) }
                .onSuccess { showResponse(it, range) }
                .onFailure { showError(it) }
        }
    }

    private suspend fun getData(range: Range) = appRepository.getChart(range.timeSpanQueryText())

    private fun showResponse(response: ChartResponse?, range: Range) =
        response?.let {
            viewState.postValue(ChartViewState.Content(response, range))
        }

    private fun showError(throwable: Throwable) {
        Timber.e(throwable)
        viewState.postValue(ChartViewState.Error(throwable))
    }
}
