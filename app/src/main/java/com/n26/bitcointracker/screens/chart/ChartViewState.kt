package com.n26.bitcointracker.screens.chart

import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.models.Range

sealed class ChartViewState {

    object Loading : ChartViewState()
    data class Content(val response: ChartResponse, val range: Range): ChartViewState()
    data class Error(val throwable: Throwable): ChartViewState()
}
