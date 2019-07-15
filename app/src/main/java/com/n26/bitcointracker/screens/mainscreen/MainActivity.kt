package com.n26.bitcointracker.screens.mainscreen

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.google.android.material.snackbar.Snackbar
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.R
import com.n26.bitcointracker.base.BaseMvpActivity
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.screens.chart.ChartFragment
import com.n26.bitcointracker.views.PillAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainContract.Presenter>(), MainContract.View,
    PillAdapter.OnPillClickListener {

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun getPresenter(): MainContract.Presenter {
        BitcoinApp.instance.baseLibComponent?.inject(this)
        return presenter
    }

    override fun showNoInternetWarning() {
        Snackbar.make(mainContainer, getString(R.string.internet_down_msg), Snackbar.LENGTH_LONG)
            .show()
    }

    override fun showChartPage(range: Range) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.mainContainer, ChartFragment.newInstance(range)).commit()
    }


    override fun onPillClicked(range: Range) = presenter.onRangeSelected(range)

    @Inject
    lateinit var presenter: MainPresenter
    val pillAdapter = PillAdapter(Range.values().toList(), this)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        with(labelsRecyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity, HORIZONTAL, false)
            adapter = pillAdapter
        }
        presenter.bindview(this)
    }
}