package com.n26.bitcointracker.screens.mainscreen

import android.os.Bundle
import android.widget.Toast
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.R
import com.n26.bitcointracker.base.BaseMvpActivity
import com.n26.bitcointracker.models.Value
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject


class MainActivity : BaseMvpActivity<MainContract.Presenter>(), MainContract.View {
    override fun showChartData(values: List<Value>) {
        try {
            val dataPoints = mutableListOf<DataPoint>()
            for (value in values) {
                dataPoints.add(
                    DataPoint(
                        value.getXAsFloat().toDouble(),
                        value.getYAsFloat().toDouble()
                    )
                )
            }
            val series = LineGraphSeries<DataPoint>(dataPoints.toTypedArray())
            chart.addSeries(series)
        } catch (e: IllegalArgumentException) {
            Toast.makeText(this@MainActivity, e.message, Toast.LENGTH_LONG).show()
        }

    }

    @Inject
    lateinit var presenter: MainPresenter

    override fun getLayoutResource(): Int = R.layout.activity_main

    override fun getPresenter(): MainContract.Presenter {
        BitcoinApp.instance.baseLibComponent?.inject(this)
        return presenter
    }

    override fun onResume() {
        super.onResume()
        presenter.bindview(this)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
//        setupChart(chart)
//        setupAxes()
    }
}

//    private fun setupAxes() {
//        with(chart.xAxis) {
//            textColor = Color.WHITE
//            setDrawGridLines(false)
//            setAvoidFirstLastClipping(true)
//            isEnabled = true
//            axisMinimum = 0f
//            axisMaximum = System.currentTimeMillis().toFloat()
//        }
//
//        with(chart.axisLeft) {
//            textColor = Color.WHITE
//            axisMinimum = 0f
//            axisMaximum = 16.0f
//            setDrawGridLines(true)
//        }

//    private fun setupChart(chart: GraphView) {
//        chart.description.isEnabled = true
//        chart.setTouchEnabled(true)
//        chart.setPinchZoom(true)
//        chart.setScaleEnabled(true)
//        chart.setDrawGridBackground(false)
//        chart.setBackgroundColor(Color.DKGRAY)
//    }


//    override fun showChartData(values: List<Value>) {
//        val dataValues = mutableListOf<Entry>()
//        for (value in values) {
//            dataValues.add(Entry(value.getXAsFloat(), value.getYAsFloat()))
//        }
//        showDataValues(dataValues)
//    }

//    private fun showDataValues(dataValues: MutableList<Entry>) {
//        var set1: LineDataSet? = null
//        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
//            set1 = chart.getData().getDataSetByIndex(0) as LineDataSet?
//            set1?.values = dataValues
//            chart.getData().notifyDataChanged()
//            chart.notifyDataSetChanged()
//        } else {
//            set1 = LineDataSet(dataValues, "Sample Data")
//            set1.setDrawIcons(false)
//            set1.enableDashedLine(10f, 5f, 0f)
//            set1.enableDashedHighlightLine(10f, 5f, 0f)
//            set1.color = Color.DKGRAY
//            set1.setCircleColor(Color.DKGRAY)
//            set1.lineWidth = 1f
//            set1.circleRadius = 3f
//            set1.setDrawCircleHole(false)
//            set1.valueTextSize = 9f
//            set1.setDrawFilled(true)
//            set1.formLineWidth = 1f
//            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
//            set1.formSize = 15f
//            if (Utils.getSDKInt() >= 18) {
//                val drawable = ContextCompat.getDrawable(this, android.R.drawable.star_big_on)
//                set1.fillDrawable = drawable
//            } else {
//                set1.fillColor = Color.DKGRAY
//            }
//            val dataSets = mutableListOf<ILineDataSet>()
//            dataSets.add(set1)
//            val data = LineData(dataSets)
//            chart.setData(data)
//        }
//        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) {
//            set1 = chart.getData().getDataSetByIndex(0) as LineDataSet?
//            set1?.values = dataValues
//            chart.getData().notifyDataChanged()
//            chart.notifyDataSetChanged()
//        } else {
//            set1 = LineDataSet(dataValues, "Sample Data")
//            set1.setDrawIcons(false)
//            set1.enableDashedLine(10f, 5f, 0f)
//            set1.enableDashedHighlightLine(10f, 5f, 0f)
//            set1.color = Color.DKGRAY
//            set1.setCircleColor(Color.DKGRAY)
//            set1.lineWidth = 1f
//            set1.circleRadius = 3f
//            set1.setDrawCircleHole(false)
//            set1.valueTextSize = 9f
//            set1.setDrawFilled(true)
//            set1.formLineWidth = 1f
//            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
//            set1.formSize = 15f
//            if (Utils.getSDKInt() >= 18) {
//                val drawable = ContextCompat.getDrawable(this, android.R.drawable.star_big_on)
//                set1.fillDrawable = drawable
//            } else {
//                set1.fillColor = Color.DKGRAY
//            }
//            val dataSets = mutableListOf<ILineDataSet>()
//            dataSets.add(set1)
//            val data = LineData(dataSets)
//            chart.setData(data)
//        }
//    }
