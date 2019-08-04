package com.n26.bitcointracker.screens.mainscreen

import com.n26.bitcointracker.BaseViewModelTest
import com.n26.bitcointracker.base.ViewEvent
import io.reactivex.observers.TestObserver
import org.junit.Before
import org.junit.Test

internal class MainViewModelTest : BaseViewModelTest<MainViewModel>() {
    private lateinit var eventObserver: TestObserver<ViewEvent>

    @Before
    override fun setup() {
        viewModel = MainViewModel(mockApp)
        eventObserver = viewModel.eventObservable.test()
    }

    @Test
    fun onPageSelected() {
        viewModel.onPageSelected(0)
        eventObserver.assertValue { it == MainViewEvent.PageSelected(0) }
    }

//    @Test
//    fun onShowToastClick() {
//        every { mockTimeProvider.currentTimeMillis } returns 1000
//        viewModel.onShowToastClick()
//        eventObserver.assertValue { it is Example1ViewEvent.ShowCurrentTimeToast && it.message == "Current unix time is 1000" }
//    }
}