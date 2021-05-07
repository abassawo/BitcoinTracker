package com.n26.bitcointracker.screens.chart

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.R
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.utils.DayValueFormatter
import com.n26.bitcointracker.utils.DollarValueFormatter
import com.n26.bitcointracker.utils.charts.ChartRenderUtil
import com.n26.bitcointracker.views.ChartMarkerView
import kotlinx.android.synthetic.main.fragment_chart.*

class ChartFragment : Fragment() {
    private lateinit var viewModel: ChartViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_chart, container, false)


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        BitcoinApp.instance.appComponent.inject(this)
        setupChart(chart)
        viewModel = ViewModelProvider(this).get(ChartViewModel::class.java)
        viewModel.viewState.observe(viewLifecycleOwner, Observer {
            handleViewState(it)
        })
        updateUI()
        swipeRefresh.setOnRefreshListener { updateUI() }
    }

    private fun handleViewState(state: ChartViewState) {
        toggleChartVisibility(visible = state !is ChartViewState.Loading)

        when (state) {
            is ChartViewState.Content -> showChartData(state)
            is ChartViewState.Error -> showChartLoadingError()
            ChartViewState.Loading -> Unit // no-op, already handled
        }
    }

    private fun updateUI() {
        val index = getRangeIndex()
        viewModel.onTimeSpanSelected(Range.values()[index])
    }

    private fun getRangeIndex(): Int = arguments?.getInt(ARG_RANGE_KEY, 0) ?: 0

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

    private fun showChartData(state: ChartViewState.Content) {
        with(state) {
            chart.description.text = getString(R.string.all_available_data_description)

            response.values?.let {
                val entries = mutableListOf<Entry>()
                for ((index, value) in it.withIndex()) {
                    val entry = Entry(index.toFloat(), value.getYAsFloat(), R.drawable.star)
                    entries.add(entry)
                }
                ChartRenderUtil.loadChart(entries, chart)
                swipeRefresh.isRefreshing = false
            }
        }
    }

    private fun toggleChartVisibility(visible: Boolean) {
        chart.visibility = if (visible) VISIBLE else GONE
    }

    private fun showChartLoadingError() =
        Toast.makeText(context, R.string.error_fetching_chart, Toast.LENGTH_LONG).show()

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
