package com.n26.bitcointracker.base

import com.n26.bitcointracker.rest.RestApi
import io.reactivex.Scheduler
import io.reactivex.internal.schedulers.IoScheduler
import javax.inject.Inject

class RepoConfig @Inject constructor(val uiScheduler: Scheduler, val restApi: RestApi)