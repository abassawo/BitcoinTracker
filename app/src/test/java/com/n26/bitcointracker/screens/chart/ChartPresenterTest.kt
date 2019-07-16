package com.n26.bitcointracker.screens.chart

import com.n26.bitcointracker.BasePresenterTest
import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.rest.AppRepository
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.Mockito.verify


class ChartPresenterTest : BasePresenterTest<ChartPresenter>() {
    @Mock
    lateinit var mockView: ChartContract.View
    val chartResponse = ChartResponse()
    @Before
    override fun setup() {
        super.setup()
        presenter = ChartPresenter(AppRepository(repoConfig))
    }

    @Test
    fun onViewBound() {
        `when`(userSettingsManager.getLastRange()).thenReturn(Range.`30_DAYS`)
        `when`(repoConfig.restApi.getChart(Range.`30_DAYS`.timeSpan)).thenReturn(
            Single.just(
                chartResponse
            )
        )
        presenter.bindview(mockView)
        verify(mockView).showChartData(chartResponse.values!!)
    }

}