package com.n26.bitcointracker.screens.mainscreen

import com.n26.bitcointracker.BasePresenterTest
import com.n26.bitcointracker.models.Range
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.Mockito.`when` as whenever

class MainPresenterTest : BasePresenterTest<MainPresenter>() {
    @Mock lateinit var mockView: MainContract.View

    @Before
    override fun setup() {
        super.setup()
        presenter = MainPresenter(mockSettings, appRepository)
    }

    @Test
    fun `test that page navigation is persisted`() {
        presenter.onPageSelected(Range.ALL.ordinal)
        verify(mockSettings).saveLastTimeSpanRange(Range.ALL)
    }

    @Test
    fun `onViewBound should load Chart if Network is Available`() {
        whenever(mockView.isNetworkAvailable()).thenReturn(true)
        presenter.bindView(mockView)
        verify(mockView, never()).showNoInternetWarning()
        verify(mockView).showChartPage(ArgumentMatchers.anyInt())
    }

    @Test
    fun `onViewBound should load Error is Network is Not Available`() {
        whenever(mockView.isNetworkAvailable()).thenReturn(false)
        presenter.bindView(mockView)
        verify(mockView).showNoInternetWarning()
        verify(mockView, never()).showChartPage(ArgumentMatchers.anyInt())
    }
}