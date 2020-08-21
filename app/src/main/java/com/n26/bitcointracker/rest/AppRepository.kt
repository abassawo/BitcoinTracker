package com.n26.bitcointracker.rest

import com.n26.bitcointracker.models.ChartResponse
import javax.inject.Inject

class AppRepository @Inject constructor(private val restApi: RestApi) {

    suspend fun getChartBlocking(range: String): ChartResponse =
        restApi.getChartBlocking(range)
}
