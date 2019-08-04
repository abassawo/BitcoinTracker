package com.n26.bitcointracker.screens.mainscreen

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.n26.bitcointracker.base.BaseViewModel
import com.n26.bitcointracker.utils.connectivity.ConnectivityUtil

class MainViewModel(val app: Application) : BaseViewModel(app) {
    val internetAvailable: LiveData<Boolean> = MutableLiveData()

    init {
        this.internetAvailable.latestValue = ConnectivityUtil.isNetworkAvailable(app)
    }

    fun onPageSelected(position: Int) {
        sendViewEvent(MainViewEvent.PageSelected(position))
    }
}
