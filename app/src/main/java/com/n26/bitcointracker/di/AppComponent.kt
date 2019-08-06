package com.n26.bitcointracker.di

import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.screens.chart.ChartFragment
import com.n26.bitcointracker.screens.mainscreen.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface  AppComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(app: BitcoinApp)
    fun inject(chartFragment: ChartFragment)
}