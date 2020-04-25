package com.n26.bitcointracker.screens.mainscreen

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.n26.bitcointracker.R
import com.n26.bitcointracker.views.adapters.TabAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewPager()
    }

    private fun setupViewPager() {
        viewPager.adapter = TabAdapter(this, supportFragmentManager)
        viewPager.apply {
            tabs.setupWithViewPager(viewPager)
            currentItem = 0
            addOnPageChangeListener(CustomPageChangeListener { position ->
                currentItem = position
            })
        }
    }
}
