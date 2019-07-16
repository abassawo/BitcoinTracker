package com.n26.bitcointracker.rest

import com.n26.bitcointracker.di.SchedulerProvider
import com.n26.bitcointracker.utils.rx.SchedulerTransformer
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val restApi: RestApi
) {

    protected fun <R> subscribeOnIoObserveOnUi(): SchedulerTransformer<R> {
        return SchedulerTransformer(schedulerProvider.ioScheduler(), schedulerProvider.uiScheduler())
    }

    fun getChart(range: String) = restApi.getChart(range).compose(subscribeOnIoObserveOnUi())
}