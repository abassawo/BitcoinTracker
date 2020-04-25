package com.n26.bitcointracker.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity

abstract class BaseMvvmActivity<V : BaseViewModel> : AppCompatActivity(),
    BaseContract.View {

    lateinit var viewModel: V

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        onViewCreated(savedInstanceState)
        viewModel = createViewModel()
    }

    /**
     * Override this method to do any additional view initialization (ex: init dagger)
     */
    protected open fun onViewCreated(savedInstanceState: Bundle?) {

    }

    abstract fun createViewModel(): V

    abstract fun getLayoutResource(): Int
}
