package com.n26.bitcointracker.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.n26.bitcointracker.BitcoinApp

abstract class BaseMvpActivity<P : BaseContract.Presenter<*>> : AppCompatActivity(), BaseContract.View {

    private lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        presenter = getPresenter()
        onViewCreated(savedInstanceState)
        presenter.onViewCreated()
    }

    /**
     * Override this method to do any additional view initialization (ex: setup RecycleView adapter)
     */
   protected open fun onViewCreated(savedInstanceState: Bundle?) {

    }

    abstract fun getPresenter(): P

    abstract fun getLayoutResource(): Int
}