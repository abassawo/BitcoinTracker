package com.n26.bitcointracker.rest

import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.models.ModelResponse
import io.reactivex.Single
import retrofit2.http.GET

public interface RestApi {

    @GET("/stats")
    fun getStats(): Single<ModelResponse>

    @GET("/charts/transactions-per-second?timespan=5weeks&rollingAverage=8hours&format=json")
    fun getChart(): Single<ChartResponse>
}