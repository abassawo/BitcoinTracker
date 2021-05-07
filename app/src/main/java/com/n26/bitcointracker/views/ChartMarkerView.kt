package com.n26.bitcointracker.views

import android.content.Context
import android.widget.TextView
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.utils.MPPointF


class ChartMarkerView(context: Context) : MarkerView(context, android.R.layout.simple_list_item_1) {
    private val tvContent: TextView = findViewById(android.R.id.text1)
    private var mOffset: MPPointF? = null

    init {
        tvContent.setBackgroundResource(android.R.color.darker_gray)
    }

    // callbacks every time the MarkerView is redrawn, can be used to update the
    // content (user-interface)
    override fun refreshContent(e: Entry, highlight: Highlight) {
        tvContent.text = """${e.y}"""
        super.refreshContent(e, highlight)
    }

    override fun getOffset(): MPPointF {
        if (mOffset == null) {
            // center the marker horizontally and vertically
            mOffset = MPPointF((-(width / 2)).toFloat(), (-height).toFloat())
        }

        return mOffset as MPPointF
    }
}