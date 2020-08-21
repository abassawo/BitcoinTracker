package com.n26.bitcointracker.base

import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel(){
    private var isViewBound: Boolean = false
}
