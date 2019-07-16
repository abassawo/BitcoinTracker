package com.n26.bitcointracker.rest

import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.base.RepoConfig
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.utils.rx.SchedulerTransformer
import io.reactivex.Scheduler
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AppRepository @Inject constructor(var repoConfig: RepoConfig) {

    @Inject
    internal lateinit var uiScheduler: Scheduler

    protected fun <R> subscribeOnIoObserveOnUi(): SchedulerTransformer<R> {
        return SchedulerTransformer(Schedulers.io(), repoConfig.uiScheduler)
    }

    fun getChart(range: String) = repoConfig.restApi.getChart(range).compose(subscribeOnIoObserveOnUi())

    fun getLastRange(): Range {
        return repoConfig.userSettingsManager.getLastRange() ?: Range.`30_DAYS`
    }
}