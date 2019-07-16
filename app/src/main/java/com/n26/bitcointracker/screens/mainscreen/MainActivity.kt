package com.n26.bitcointracker.screens.mainscreen

import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.R
import com.n26.bitcointracker.adapters.TabAdapter
import com.n26.bitcointracker.base.BaseMvpActivity
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainContract.Presenter>(), MainContract.View {
    @Inject
    lateinit var presenter: MainPresenter

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun getPresenter(): MainContract.Presenter = presenter

    override fun showNoInternetWarning() {
        Snackbar.make(viewPager, getString(R.string.internet_down_msg), Snackbar.LENGTH_LONG)
            .show()
    }

    override fun showChartPage(rangeIndex: Int) {
        viewPager.setCurrentItem(rangeIndex)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        BitcoinApp.instance.baseLibComponent?.inject(this)
        with(viewPager) {
            adapter = TabAdapter(this@MainActivity, supportFragmentManager)
            currentItem = 0
            tabs.setupWithViewPager(this)
        }
        presenter.bindview(this)
    }

    override fun isNetworkAvailable(): Boolean {
        return true //todo
    }

}