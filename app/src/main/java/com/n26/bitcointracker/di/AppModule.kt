package com.n26.bitcointracker.di

import android.content.Context
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.settings.UserSettings
import com.n26.bitcointracker.settings.UserSettingsManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val application: BitcoinApp) {
    @Provides
    @Singleton
    fun provideAppContext(): Context = application

    @Provides
    @Singleton
    fun provideUserSettingsManager(): UserSettings = UserSettingsManager(application)

    companion object {
        const val BASE_URL = "https://api.blockchain.info/"
    }
}
