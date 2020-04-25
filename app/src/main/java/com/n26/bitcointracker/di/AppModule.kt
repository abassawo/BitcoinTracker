package com.n26.bitcointracker.di

import android.content.Context
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.BuildConfig
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.rest.RestApi
import com.n26.bitcointracker.settings.UserSettings
import com.n26.bitcointracker.settings.UserSettingsManager
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule(private val application: BitcoinApp) {
    @Provides
    @Singleton
    fun provideAppContext(): Context = application


    @Provides
    @Singleton
    fun provideUserSettingsManager(): UserSettings = UserSettingsManager(application)


    @Provides
    @Singleton
    fun provideSchedulerProvider(): SchedulerProvider = AppSchedulerProvider()

    companion object {
        const val BASE_URL = "https://api.blockchain.info/"
    }
}
