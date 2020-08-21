package com.n26.bitcointracker.rest

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.n26.bitcointracker.di.AppModule
import com.n26.bitcointracker.models.ChartResponse
import io.reactivex.Single
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface RestApi {
    @GET("charts/market-price")
    fun getChart(@Query("timespan") timeSpan: String): Single<ChartResponse>

    @GET("charts/market-price")
    suspend fun getChartBlocking(@Query("timespan") timeSpan: String): ChartResponse
}


object RetrofitClient {

    fun getService(): RestApi =
        Retrofit.Builder().baseUrl(AppModule.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addNetworkInterceptor(StethoInterceptor())
                    .build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .build()
            .create(RestApi::class.java)

}
