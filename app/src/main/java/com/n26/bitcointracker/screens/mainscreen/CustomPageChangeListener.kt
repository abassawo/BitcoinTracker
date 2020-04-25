package com.n26.bitcointracker.screens.mainscreen

import androidx.viewpager.widget.ViewPager
import androidx.viewpager.widget.ViewPager.*

class CustomPageChangeListener(val selectAction: (position: Int) -> Unit) : OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) {}

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}

    override fun onPageSelected(position: Int) {
        selectAction(position)
    }

}
