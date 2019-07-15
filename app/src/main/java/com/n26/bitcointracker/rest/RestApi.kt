package com.n26.bitcointracker.rest

import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.models.ModelResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

public interface RestApi {

    @GET("/stats")
    fun getStats(): Single<ModelResponse>

    @GET("https://api.blockchain.info/charts/market-price")
    fun getChart(@Query("timeSpan") timeSpan: String): Single<ChartResponse>
}