package com.n26.bitcointracker.screens.chart

import android.graphics.Color
import android.os.Bundle
import android.view.View
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.R
import com.n26.bitcointracker.base.BaseMvpFragment
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.models.Value
import com.n26.bitcointracker.utils.charts.ChartUtil
import kotlinx.android.synthetic.main.fragment_chart.*
import javax.inject.Inject

class ChartFragment : BaseMvpFragment<ChartContract.Presenter>(), ChartContract.View {
    @Inject
    lateinit var presenter: ChartPresenter

    override fun getLayoutResourceId(): Int = R.layout.fragment_chart

    override fun getPresenter(): ChartContract.Presenter {
        BitcoinApp.instance.baseLibComponent?.inject(this)
        return presenter
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupChart(chart)
        val range = arguments?.getString(ARG_RANGE_KEY) ?: Range.ALL.timeSpan
        presenter.bindview(this)
        presenter.onRangeSelected(range)
    }

    private fun setupChart(chart: LineChart) {
        chart.setBackgroundColor(Color.WHITE)
    }

    override fun showChartData(values: List<Value>) {
        chart.clear()
        val entries = mutableListOf<Entry>()
        for ((index, value) in values.withIndex()) {
            val entry = Entry(index.toFloat(), value.getYAsFloat(), R.drawable.star)
            entries.add(entry)
        }
        ChartUtil.loadChart(entries, chart)
    }

    override fun clearChart() = chart.clear()

    companion object {
        private val ARG_RANGE_KEY = "argRange"

        fun newInstance(range: Range): ChartFragment {
            val args = Bundle()
            args.putString(ARG_RANGE_KEY, range.timeSpan)
            val fragment = ChartFragment()
            fragment.arguments = args
            return fragment
        }
    }
}