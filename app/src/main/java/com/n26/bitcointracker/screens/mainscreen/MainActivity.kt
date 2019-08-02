package com.n26.bitcointracker.screens.mainscreen

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.viewpager.widget.ViewPager
import com.google.android.material.snackbar.Snackbar
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.R
import com.n26.bitcointracker.views.adapters.TabAdapter
import com.n26.bitcointracker.base.BaseMvvmActivity
import com.n26.bitcointracker.base.BaseViewModel
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.settings.UserSettings
import com.n26.bitcointracker.settings.UserSettingsManager
import com.n26.bitcointracker.utils.connectivity.ConnectivityUtil
import kotlinx.android.synthetic.main.activity_main.*
import java.awt.font.NumericShaper
import javax.inject.Inject

class MainActivity : BaseMvvmActivity<MainViewModel>() {
    @Inject
    lateinit var userSettings: UserSettings

    override fun createViewModel() =
        ViewModelProviders.of(this).get(MainViewModel::class.java)

    override fun getLayoutResource() = R.layout.activity_main

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        BitcoinApp.instance.appComponent?.inject(this)
        setupViewPager(viewPager)
        viewModel.internetAvailable.observe(
            this@MainActivity, Observer { e -> processNetworkAvailability(e) })
    }

    private fun setupViewPager(viewPager: ViewPager) {
        with(viewPager) {
            adapter = TabAdapter(this@MainActivity, supportFragmentManager)
            currentItem = 0
            tabs.setupWithViewPager(this)

            viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {

                override fun onPageScrollStateChanged(state: Int) {}

                override fun onPageScrolled(
                    position: Int,
                    positionOffset: Float,
                    positionOffsetPixels: Int
                ) {
                }

                override fun onPageSelected(position: Int) {
                    userSettings.saveLastTimeSpanRange(Range.values()[position])
                }
            })
        }
    }

    private fun processNetworkAvailability(networkAvailable: Boolean) {
        if (networkAvailable) {
            showChartPage(0)
        } else {
            showNoInternetWarning()
        }
    }

    private fun showNoInternetWarning() {
        Snackbar.make(
            viewPager,
            getString(R.string.internet_down_msg),
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(R.string.check_internet_settings) {
                startActivity(Intent(Settings.ACTION_WIRELESS_SETTINGS))
            }
            .show()
    }

    private fun showChartPage(rangeIndex: Int) {
        viewPager.currentItem = rangeIndex
    }
}