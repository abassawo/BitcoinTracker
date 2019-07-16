package com.n26.bitcointracker.rest

import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.models.StatResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

public interface RestApi {

    @GET("/stats")
    fun getStats(): Single<StatResponse>

    @GET("https://api.blockchain.info/charts/market-price")
    fun getChart(@Query("timespan") timeSpan: String): Single<ChartResponse>
}