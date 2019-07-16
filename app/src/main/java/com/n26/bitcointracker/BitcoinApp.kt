package com.n26.bitcointracker

import android.app.Application
import com.n26.bitcointracker.di.AppComponent
import com.n26.bitcointracker.di.AppModule
import com.n26.bitcointracker.di.DaggerAppComponent
import timber.log.Timber

class BitcoinApp : Application() {
    var appComponent: AppComponent? = null

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
        appComponent?.inject(this)
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        lateinit var instance: BitcoinApp
            private set
    }
}