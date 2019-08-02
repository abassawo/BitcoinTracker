package com.n26.bitcointracker.screens.mainscreen

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.n26.bitcointracker.base.BaseViewModel
import com.n26.bitcointracker.utils.connectivity.ConnectivityUtil

class MainViewModel(val app: Application) : BaseViewModel(app), MainContract.ViewModel {
    val internetAvailable: MutableLiveData<Boolean> = MutableLiveData()

    fun onMainStarted() {
        this.internetAvailable.value = ConnectivityUtil.isNetworkAvailable(app)
    }

    init {
        onMainStarted()
    }
}