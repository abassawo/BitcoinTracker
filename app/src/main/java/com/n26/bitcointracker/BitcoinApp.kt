package com.n26.bitcointracker

import android.app.Application
import com.n26.bitcointracker.di.DaggerBaseComponent

class BitcoinApp : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
        val baseLibComponent = DaggerBaseComponent.create()
        baseLibComponent.inject(this)

    }

    companion object {
        lateinit var instance: BitcoinApp
            private set
    }
}