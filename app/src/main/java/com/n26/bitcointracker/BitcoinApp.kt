package com.n26.bitcointracker

import android.app.Application
import com.n26.bitcointracker.di.BaseComponent
import com.n26.bitcointracker.di.DaggerBaseComponent

class BitcoinApp : Application() {
    var baseLibComponent: BaseComponent? = null

    override fun onCreate() {
        super.onCreate()
        instance = this
        baseLibComponent = DaggerBaseComponent.create()
        baseLibComponent?.inject(this)

    }

    companion object {
        lateinit var instance: BitcoinApp
            private set
    }
}