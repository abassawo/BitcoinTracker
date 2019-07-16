package com.n26.bitcointracker.rest

import com.n26.bitcointracker.models.ChartResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    @GET("https://api.blockchain.info/charts/market-price")
    fun getChart(@Query("timespan") timeSpan: String): Single<ChartResponse>
}