package com.n26.bitcointracker.testutil

import com.n26.bitcointracker.base.BasePresenter
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.rest.RestApi
import com.n26.bitcointracker.settings.UserSettings
import org.junit.Before
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@RunWith(MockitoJUnitRunner::class)
open class BasePresenterTest<P : BasePresenter<*>> {
    lateinit var appRepository: AppRepository
    lateinit var presenter: P
    @Mock
    lateinit var mockRestApi: RestApi
    @Mock
    lateinit var mockSettings: UserSettings
    lateinit var testSchedulerProvider: TestSchedulerProvider

    @Before
    open fun setup() {
        testSchedulerProvider = TestSchedulerProvider()
        appRepository = AppRepository(testSchedulerProvider, mockRestApi)
    }
}