package com.n26.bitcointracker.screens.mainscreen

import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.base.RepoConfig
import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.rest.RestApi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.TestScheduler
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
//import org.powermock.core.classloader.annotations.PrepareForTest

@RunWith(MockitoJUnitRunner::class)
class MainPresenterTest {
    protected lateinit var presenter: MainPresenter
    @Mock lateinit var mockView: MainContract.View
    @Mock lateinit var mockRestApi: RestApi

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this   )
        val repoConfig = RepoConfig(TestScheduler(), mockRestApi)
        presenter = MainPresenter(AppRepository(repoConfig))
        presenter.bindview(mockView)
    }

    @Test
    fun onViewBound() {
        presenter.onViewBound()
        verify(mockView, never()).showNoInternetWarning()
    }

    @Test
    fun onConnectivityChecked() {
    }
}