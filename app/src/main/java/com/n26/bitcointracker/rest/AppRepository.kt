package com.n26.bitcointracker.rest

import com.n26.bitcointracker.di.SchedulerProvider
import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.utils.rx.SchedulerTransformer
import io.reactivex.Single
import javax.inject.Inject

class AppRepository @Inject constructor(
    private val schedulerProvider: SchedulerProvider,
    private val restApi: RestApi
) {

    private fun <R> subscribeOnIoObserveOnUi(): SchedulerTransformer<R> =
        SchedulerTransformer(schedulerProvider.ioScheduler(), schedulerProvider.uiScheduler())

    fun getChart(range: String): Single<ChartResponse> =
        restApi.getChart(range)
            .compose(subscribeOnIoObserveOnUi())
}
