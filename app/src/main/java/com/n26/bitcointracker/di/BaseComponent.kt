package com.n26.bitcointracker.di

import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.base.BaseMvpActivity
import com.n26.bitcointracker.screens.mainscreen.MainActivity
import com.n26.bitcointracker.screens.mainscreen.MainPresenter
import dagger.Component
import javax.inject.Singleton

@Component(modules = [NetworkModule::class])
@Singleton
interface  BaseComponent {
    fun inject(mainActivity: MainActivity)
    fun inject(app: BitcoinApp)
    fun inject(mainPresenter: MainPresenter)
}