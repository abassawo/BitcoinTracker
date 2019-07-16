package com.n26.bitcointracker.screens.mainscreen

import com.n26.bitcointracker.BasePresenterTest
import com.n26.bitcointracker.rest.AppRepository
import org.junit.Before
import org.junit.Test
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*

class MainPresenterTest : BasePresenterTest<MainPresenter>() {
    @Mock lateinit var mockView: MainContract.View

    @Before
    override fun setup() {
        super.setup()
        presenter = MainPresenter(AppRepository(repoConfig))
    }

    @Test
    fun `onViewBound should load Chart if Network is Available`() {
        `when`(mockView.isNetworkAvailable()).thenReturn(true)
        presenter.bindview(mockView)
        verify(mockView, never()).showNoInternetWarning()
        verify(mockView).showChartPage(ArgumentMatchers.anyInt())
    }

    @Test
    fun `onViewBound should load Error is Network is Not Available`() {
        `when`(mockView.isNetworkAvailable()).thenReturn(false)
        presenter.bindview(mockView)
        verify(mockView).showNoInternetWarning()
        verify(mockView, never()).showChartPage(ArgumentMatchers.anyInt())
    }
}