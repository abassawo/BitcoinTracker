package com.n26.bitcointracker

import android.app.Application
import com.facebook.stetho.Stetho
import com.n26.bitcointracker.di.AppComponent
import com.n26.bitcointracker.di.DaggerAppComponent
import com.n26.bitcointracker.di.AppModule
import timber.log.Timber

class BitcoinApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initAppComponent()
        appComponent.inject(this)
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        Stetho.initializeWithDefaults(this)
    }

    private fun initAppComponent() =
         DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()

    companion object {
        lateinit var instance: BitcoinApp
            private set
    }
}
