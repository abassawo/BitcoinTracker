package com.n26.bitcointracker.screens.chart

import com.n26.bitcointracker.base.ViewEvent
import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.models.Range

sealed class ChartViewEvent: ViewEvent {
    data class ErrorCase(val errorMsg: String) : ChartViewEvent()
    data class DataLoaded(val chartResponse: ChartResponse, val range: Range) : ChartViewEvent()
}