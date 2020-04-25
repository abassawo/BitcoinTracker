package com.n26.bitcointracker.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class BaseViewModel : ViewModel(){

    val disposables: CompositeDisposable = CompositeDisposable()
    private var isViewBound: Boolean = false


}
