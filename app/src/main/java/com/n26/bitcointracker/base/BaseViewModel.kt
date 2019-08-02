package com.n26.bitcointracker.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel


abstract class BaseViewModel(appContext: Application) : AbstractViewModel(appContext)

abstract class AbstractViewModel(appContext: Application): AndroidViewModel(appContext)