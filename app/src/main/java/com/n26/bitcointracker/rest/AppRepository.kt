package com.n26.bitcointracker.rest

import com.n26.bitcointracker.models.ChartResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppRepository @Inject constructor(private val restApi: RestApi) {

    suspend fun getChart(range: String): ChartResponse =
        restApi.getChart(range)
}
