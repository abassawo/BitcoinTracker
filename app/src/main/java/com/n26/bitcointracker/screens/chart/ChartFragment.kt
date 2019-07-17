package com.n26.bitcointracker.screens.chart

import android.graphics.Color
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.R
import com.n26.bitcointracker.base.BaseMvpFragment
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.models.Value
import com.n26.bitcointracker.utils.DayValueFormatter
import com.n26.bitcointracker.utils.DollarValueFormatter
import com.n26.bitcointracker.utils.charts.ChartRenderUtil
import com.n26.bitcointracker.views.ChartMarkerView
import kotlinx.android.synthetic.main.fragment_chart.*
import javax.inject.Inject

class ChartFragment : BaseMvpFragment<ChartContract.Presenter>(), ChartContract.View {
    @Inject
    lateinit var presenter: ChartPresenter

    override fun getLayoutResourceId(): Int = R.layout.fragment_chart

    override fun getPresenter(): ChartContract.Presenter = presenter

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        BitcoinApp.instance.appComponent?.inject(this)
        setupChart(chart)
        updateUI()
        swipeRefresh.setOnRefreshListener { updateUI() }
    }

    private fun updateUI() {
        presenter.bindView(this)
        getRangeIndex().let {
            presenter.onTimeSpanSelected(Range.values()[it])
        }
    }

    private fun getRangeIndex(): Int {
        return arguments?.getInt(ARG_RANGE_KEY, 0) ?: 0
    }

    private fun setupChart(chart: LineChart) {
        chart.setTouchEnabled(true)
        chart.isHighlightPerTapEnabled = true
        context?.let {
            chart.marker = ChartMarkerView(it)
        }
        chart.description.isEnabled = true
        chart.setBackgroundColor(Color.WHITE)
        val yAxis = chart.axisRight
        yAxis.isEnabled = false

        val leftYAxis = chart.axisLeft
        leftYAxis.valueFormatter = DollarValueFormatter()

        val xAxis = chart.xAxis
        xAxis.valueFormatter = DayValueFormatter()
    }

    override fun showChartData(values: List<Value>?, range: Range) {
        if(range == Range.ALL) {
            chart.description.text = getString(R.string.all_available_data_description)
        } else {
            chart.description.text = getString(R.string.generic_chart_text, range.timeSpan)
        }

        values?.let {
            val entries = mutableListOf<Entry>()
            for ((index, value) in it.withIndex()) {
                val entry = Entry(index.toFloat(), value.getYAsFloat(), R.drawable.star)
                entries.add(entry)
            }
            ChartRenderUtil.loadChart(entries, chart)
            swipeRefresh.isRefreshing = false
        }
    }


    override fun toggleChartVisibility(visible: Boolean) {
        chart.visibility = if (visible) VISIBLE else GONE
    }


    override fun showChartLoadingError() {
        Toast.makeText(context, R.string.error_fetching_chart, Toast.LENGTH_LONG).show()
    }

    companion object {
        private const val ARG_RANGE_KEY = "argRange"

        fun newInstance(range: Range): ChartFragment {
            val args = Bundle()
            args.putInt(ARG_RANGE_KEY, range.ordinal)
            val fragment = ChartFragment()
            fragment.arguments = args
            return fragment
        }
    }
}