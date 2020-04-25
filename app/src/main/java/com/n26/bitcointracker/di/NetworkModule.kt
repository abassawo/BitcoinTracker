package com.n26.bitcointracker.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.n26.bitcointracker.BuildConfig
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.rest.RestApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import okhttp3.logging.HttpLoggingInterceptor.*
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun providesGson(): Gson = GsonBuilder().create()

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient =
        with(HttpLoggingInterceptor()) {
            OkHttpClient.Builder()
                .addInterceptor(this.addLog())
                .build()
        }

    private fun HttpLoggingInterceptor.addLog() = also {
        this.level = if (BuildConfig.DEBUG) Level.BODY else Level.NONE
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit =
        Retrofit.Builder().baseUrl(AppModule.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideRestApi(retrofit: Retrofit): RestApi = retrofit.create(RestApi::class.java)

    @Provides
    @Singleton
    fun provideRepository(
        restApi: RestApi,
        schedulerProvider: SchedulerProvider
    ): AppRepository = AppRepository(schedulerProvider, restApi)
}

