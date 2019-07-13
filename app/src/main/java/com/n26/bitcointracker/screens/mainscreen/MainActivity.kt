package com.n26.bitcointracker.screens.mainscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.n26.bitcointracker.R
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    @Inject lateinit var presenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
