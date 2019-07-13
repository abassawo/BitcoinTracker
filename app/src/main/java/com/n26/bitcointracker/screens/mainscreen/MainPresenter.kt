package com.n26.bitcointracker.screens.mainscreen

import android.util.Log
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.base.BasePresenter
import javax.inject.Inject

class MainPresenter @Inject constructor() :
    BasePresenter<MainContract.View>(), MainContract.Presenter {

    private val TAG: String? = " MainPresenter"

    override

    fun onViewCreated() {
        super.onViewCreated()
        BitcoinApp.instance.baseLibComponent?.inject(this)
        Log.d("MainPresenter", "on view created")
        val disposable = restApi.getStats()
            .compose(subscribeOnIoObserveOnUi())
            .subscribe(
                { response -> Log.d("MainPresenter", response.toString()) },
                { e -> Log.d(TAG, e.message) })

        disposables.add(disposable)
    }
}