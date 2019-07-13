package com.n26.bitcointracker.utils.rx

import android.util.Log
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

object RxUtils {

    private val TAG: String = "RxUtils"

    @NonNull
    fun logError(): Consumer<Throwable> =
      Consumer { t -> t?.message?.let { Log.d(TAG, it) }  }

    fun unsubscribeDisposable(@Nullable disposable: Disposable?) {
        disposable?.dispose()
    }
}