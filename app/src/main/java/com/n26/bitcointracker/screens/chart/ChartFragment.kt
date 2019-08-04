package com.n26.bitcointracker.screens.chart

import android.graphics.Color
import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.R
import com.n26.bitcointracker.base.BaseMvpFragment
import com.n26.bitcointracker.base.BaseViewModel
import com.n26.bitcointracker.base.ViewEvent
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.models.Value
import com.n26.bitcointracker.screens.mainscreen.MainViewModel
import com.n26.bitcointracker.utils.DayValueFormatter
import com.n26.bitcointracker.utils.DollarValueFormatter
import com.n26.bitcointracker.utils.charts.ChartRenderUtil
import com.n26.bitcointracker.views.ChartMarkerView
import kotlinx.android.synthetic.main.fragment_chart.*
import javax.inject.Inject

class ChartFragment : BaseMvpFragment<ChartViewModel>() {
    override fun createViewModel(): ChartViewModel =
        ViewModelProviders.of(this).get(ChartViewModel::class.java)

    override fun getLayoutResourceId(): Int = R.layout.fragment_chart


    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        BitcoinApp.instance.appComponent?.inject(this)
        setupChart(chart)
        updateUI()
        swipeRefresh.setOnRefreshListener { updateUI() }
    }

    override fun onViewEvent(event: ViewEvent) {
        when (event) {
            is ChartViewEvent.DataLoaded -> with(event) {
                showChartData(chartResponse.values, range)
            }
            is ChartViewEvent.ErrorCase -> showChartLoadingError()
        }
    }

    private fun updateUI() {
        getRangeIndex().let {
            viewModel.onTimeSpanSelected(Range.values()[it])
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

    fun showChartData(values: List<Value>?, range: Range) {
        if (range == Range.ALL) {
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

    fun showChartLoadingError() {
        context?.let {  Toast.makeText(it, R.string.error_fetching_chart, Toast.LENGTH_LONG).show() }
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