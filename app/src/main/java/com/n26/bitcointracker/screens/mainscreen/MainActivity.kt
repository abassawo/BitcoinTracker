package com.n26.bitcointracker.screens.mainscreen

import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.R
import com.n26.bitcointracker.base.BaseMvpActivity
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.models.Value
import com.n26.bitcointracker.utils.charts.ChartUtil
import com.n26.bitcointracker.views.PillAdapter
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainContract.Presenter>(), MainContract.View,
    PillAdapter.OnPillClickListener {

    override fun clearChart() {
        chart.clear()
    }

    override fun onPillClicked(range: Range) = presenter.onRangeSelected(range)

    @Inject
    lateinit var presenter: MainPresenter
    val pillAdapter = PillAdapter(Range.values().toList(), this)

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun getPresenter(): MainContract.Presenter {
        BitcoinApp.instance.baseLibComponent?.inject(this)
        return presenter
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        with(labelsRecyclerView) {
            layoutManager = LinearLayoutManager(this@MainActivity, HORIZONTAL, false)
            adapter = pillAdapter
        }
        setupChart(chart)
    }

    override fun onResume() {
        super.onResume()
        presenter.bindview(this)
    }

    private fun setupChart(chart: LineChart) {
        chart.setBackgroundColor(Color.WHITE)
    }

    override fun showChartData(values: List<Value>, range: Range) {
        val entries = mutableListOf<Entry>()
        for ((index, value) in values.withIndex()) {
            val entry = Entry(index.toFloat(), value.getYAsFloat(), R.drawable.star)
            entries.add(entry)
        }
        ChartUtil.loadChart(entries, chart)
    }
}