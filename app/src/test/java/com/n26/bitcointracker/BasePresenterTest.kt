package com.n26.bitcointracker

import com.n26.bitcointracker.base.BasePresenter
import com.n26.bitcointracker.base.RepoConfig
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.rest.RestApi
import com.n26.bitcointracker.settings.UserSettingsManager
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
open class BasePresenterTest<P : BasePresenter<*>> {
    lateinit var repoConfig: RepoConfig
    lateinit var appRepository: AppRepository
    lateinit var presenter: P
    @Mock
    lateinit var mockRestApi: RestApi
    @Mock
    lateinit var userSettingsManager: UserSettingsManager

    @Before
    open fun setup() {
        repoConfig = RepoConfig(TestScheduler(), userSettingsManager, mockRestApi)
        appRepository = AppRepository(repoConfig)
    }

}