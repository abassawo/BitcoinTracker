package com.n26.bitcointracker

import com.n26.bitcointracker.base.BaseViewModel
import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.rest.RestApi
import com.n26.bitcointracker.settings.UserSettings
import com.n26.bitcointracker.testutil.TestSchedulerProvider
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.Mockito.`when` as whenever

@RunWith(MockitoJUnitRunner::class)
open class BasePresenterTest<P : BaseViewModel> {
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

    @Test
    fun testAppRepository() {
        whenever(mockRestApi.getChart(Range.ALL.timeSpan)).thenReturn(Single.just(ChartResponse()))
        val testObserver = appRepository.getChart(Range.ALL.timeSpan).test()
        testObserver.assertNoErrors()
    }
}
