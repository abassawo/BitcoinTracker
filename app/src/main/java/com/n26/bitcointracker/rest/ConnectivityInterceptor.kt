package com.n26.bitcointracker.rest

import android.content.Context
import com.n26.bitcointracker.R
import io.reactivex.Single
import okhttp3.Interceptor
import okhttp3.Response
import timber.log.Timber
import java.io.IOException

class ConnectivityInterceptor(val context: Context, isNetworkActive: Single<Boolean>) : Interceptor {

    private var isNetworkActive: Boolean = false

    init {
        isNetworkActive.subscribe(
            { isNetworkActive -> this.isNetworkActive = isNetworkActive },
            { error -> Timber.e(error.message) })
    }

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        return if (!isNetworkActive) {
            throw NoConnectivityException(context)
        } else {
            chain.proceed(chain.request())
        }
    }
}

class NoConnectivityException(val context: Context) : IOException() {

    override val message: String? = context.getString(R.string.internet_down_msg)
}