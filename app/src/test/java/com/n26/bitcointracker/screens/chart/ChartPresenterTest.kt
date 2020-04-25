package com.n26.bitcointracker.screens.chart

import com.n26.bitcointracker.BasePresenterTest
import com.n26.bitcointracker.models.ChartResponse
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.models.Value
import com.n26.bitcointracker.models.getTimeSpanQueryText
import com.n26.bitcointracker.testutil.ImmediateSchedulerRule
import io.reactivex.Single
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when` as whenever

@Rule
@JvmField
val immediateSchedulerRule = ImmediateSchedulerRule()
class ChartPresenterTest : BasePresenterTest<ChartViewModel>() {
    @Mock
    lateinit var mockView: ChartContract.View

    @Before
    override fun setup() {
        super.setup()
        presenter = ChartViewModel(mockSettings, appRepository)
        presenter.bindView(mockView)
    }

    @Test
    fun `onViewBound test successful response`() {
        //Arrange
        val chartResponse = ChartResponse()
        chartResponse.values = listOf(Value())
        val single = Single.create<ChartResponse>{emitter
            -> emitter.onSuccess(chartResponse)}
        whenever(mockRestApi.getChart(Range.ALL.getTimeSpanQueryText())).thenReturn(single)

        //Act
        presenter.onTimeSpanSelected(Range.ALL)

        testSchedulerProvider.testScheduler.triggerActions()
        verify(mockView).showChartData(chartResponse.values, Range.ALL)
    }


    @Test
    fun `test error case should trigger view to show error`() {
        //Arrange
        val error = "Test error"
        val single = Single.create<ChartResponse> {
                emitter ->
            emitter.onError(Exception(error))
        }
        whenever(mockRestApi.getChart(Range.ALL.getTimeSpanQueryText())).thenReturn(single)

        //Act
        presenter.onTimeSpanSelected(Range.ALL)

        //Assert
        testSchedulerProvider.testScheduler.triggerActions()
        verify(mockView).showChartLoadingError()
    }
}
