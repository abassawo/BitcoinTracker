package com.n26.bitcointracker.base

import com.n26.bitcointracker.rest.RestApi
import com.n26.bitcointracker.settings.UserSettingsManager
import io.reactivex.Scheduler
import javax.inject.Inject

class RepoConfig @Inject constructor(val uiScheduler: Scheduler, val userSettingsManager: UserSettingsManager, val restApi: RestApi)