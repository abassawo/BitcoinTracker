package com.n26.bitcointracker.rest

import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.n26.bitcointracker.di.AppModule
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {

    fun getService() =
        Retrofit.Builder().baseUrl(AppModule.BASE_URL)
            .client(
                OkHttpClient.Builder()
                    .addNetworkInterceptor(StethoInterceptor())
                    .build())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(RestApi::class.java)

}
