package com.n26.bitcointracker.views.adapters

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.n26.bitcointracker.models.Range
import com.n26.bitcointracker.screens.chart.ChartFragment

class TabAdapter(context: Context, fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    private val fragments = mutableListOf<Fragment>()
    private val titles = mutableListOf<String>()

    init {
        for (value in Range.values()) {
            addFragment(ChartFragment.newInstance(value), context.getString(value.friendlyName))
        }
        notifyDataSetChanged()
    }

    private fun addFragment(fragment: Fragment, title: String) {
        fragments.add(fragment)
        titles.add(title)
    }

    override fun getItem(position: Int) = fragments[position]

    override fun getCount() = fragments.size

    override fun getPageTitle(position: Int): CharSequence? {
        return titles[position]
    }

}