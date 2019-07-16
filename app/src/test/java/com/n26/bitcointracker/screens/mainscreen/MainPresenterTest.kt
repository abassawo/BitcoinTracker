package com.n26.bitcointracker.screens.mainscreen

import org.junit.Test

import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoJUnitRunner
import org.powermock.modules.junit4.PowerMockRunner

//@RunWith(PowerMockRunner::class)
@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {
    protected var presenter: MainPresenter = MainPresenter()
    @Mock lateinit var mockView: MainContract.View

    @Before
    fun setup() {
        presenter.bindview(mockView)
    }

    @Test
    fun onViewBound() {
        presenter.onViewBound()

    }

    @Test
    fun onConnectivityChecked() {
    }
}