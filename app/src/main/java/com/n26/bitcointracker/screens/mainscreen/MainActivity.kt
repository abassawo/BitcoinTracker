package com.n26.bitcointracker.screens.mainscreen

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.R
import com.n26.bitcointracker.adapters.TabAdapter
import com.n26.bitcointracker.base.BaseMvpActivity
import com.n26.bitcointracker.utils.connectivity.ConnectivityUtil
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainContract.Presenter>(), MainContract.View {
    @Inject
    lateinit var presenter: MainPresenter

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun getPresenter(): MainContract.Presenter = presenter

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        BitcoinApp.instance.appComponent?.inject(this)
        with(viewPager) {
            adapter = TabAdapter(this@MainActivity, supportFragmentManager)
            currentItem = 0
            tabs.setupWithViewPager(this)


            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                override fun onPageScrollStateChanged(state: Int) {
                }

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {

                }

                override fun onPageSelected(position: Int) {
                    presenter.onPageSelected(position)
                }

            })
        }
        presenter.bindview(this)
    }

    override fun showNoInternetWarning() {
        Snackbar.make(viewPager, getString(R.string.internet_down_msg), Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.check_internet_settings, {
                startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
            })
            .show()
    }

    override fun showChartPage(rangeIndex: Int) {
        viewPager.setCurrentItem(rangeIndex)
    }

    override fun isNetworkAvailable(): Boolean {
        return ConnectivityUtil.isNetworkAvailable(this)
    }
}