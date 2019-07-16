package com.n26.bitcointracker.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.n26.bitcointracker.AppConfig
import com.n26.bitcointracker.base.RepoConfig
import com.n26.bitcointracker.rest.RestApi
import com.n26.bitcointracker.settings.UserSettingsManager
import dagger.Module
import dagger.Provides
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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
    fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient, gson: Gson): Retrofit {
        return Retrofit.Builder().baseUrl(AppConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideRestApi(retrofit: Retrofit) = retrofit.create(RestApi::class.java)

    @Provides
    @Singleton
    fun provideUiScheduler(): Scheduler = AndroidSchedulers.mainThread()

    @Provides
    @Singleton
    fun provideUserSettingsManager(): UserSettingsManager = UserSettingsManager()

    @Provides
    @Singleton
    fun provideRepoConfig(
        restApi: RestApi,
        userSettingsManager: UserSettingsManager,
        uiScheduler: Scheduler
    ): RepoConfig {
        return RepoConfig(uiScheduler, userSettingsManager, restApi)
    }
}