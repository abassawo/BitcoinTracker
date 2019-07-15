package com.n26.bitcointracker.utils.charts

import android.graphics.Color
import android.graphics.DashPathEffect
import androidx.core.content.ContextCompat
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IFillFormatter
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.Utils
import com.n26.bitcointracker.R
import java.util.*

object ChartUtil {

    fun loadChart(entries: List<Entry>, chart: LineChart) {
        val set1: LineDataSet

        if (chart.getData() != null && chart.getData().getDataSetCount() > 0) run {
            set1 = chart.getData().getDataSetByIndex(0) as LineDataSet
            set1.values = entries
            set1.notifyDataSetChanged()
            chart.getData().notifyDataChanged()
            chart.notifyDataSetChanged()
            chart.invalidate()
        } else {
            // create a dataset and give it a type
            set1 = LineDataSet(entries, "DataSet 1")

            set1.setDrawIcons(false)

            // draw dashed line
            set1.enableDashedLine(10f, 5f, 0f)

            // black lines and points
            set1.color = Color.BLACK
            set1.setCircleColor(Color.BLACK)

            // line thickness and point size
            set1.lineWidth = 1f
            set1.circleRadius = 3f

            // draw points as solid circles
            set1.setDrawCircleHole(false)

            // customize legend entry
            set1.formLineWidth = 1f
            set1.formLineDashEffect = DashPathEffect(floatArrayOf(10f, 5f), 0f)
            set1.formSize = 15f

            // text size of values
            set1.valueTextSize = 9f

            // draw selection line as dashed
            set1.enableDashedHighlightLine(10f, 5f, 0f)

            // set the filled area
            set1.setDrawFilled(true)
            set1.fillFormatter =
                IFillFormatter { dataSet, dataProvider -> chart.getAxisLeft().getAxisMinimum() }

            // set color of filled area
            if (Utils.getSDKInt() >= 18) {
                // drawables only supported on api level 18 and above
                val drawable = ContextCompat.getDrawable(chart.context, R.drawable.fade_red)
                set1.fillDrawable = drawable
            } else {
                set1.fillColor = Color.BLACK
            }

            val dataSets = ArrayList<ILineDataSet>()
            dataSets.add(set1) // add the data sets

            // create a data object with the data sets
            val data = LineData(dataSets)

            // set data
            chart.setData(data)
            chart.invalidate()
            chart.notifyDataSetChanged()
        }
    }
}