package com.n26.bitcointracker.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.appcompat.app.AppCompatActivity

abstract class BaseMvpActivity<P : BaseContract.Presenter<*>> : AppCompatActivity(),
    BaseContract.View {

    private lateinit var presenter: P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        onViewCreated(savedInstanceState)
        presenter = getPresenter()
    }

    /**
     * Override this method to do any additional view initialization (ex: init dagger)
     */
    protected open fun onViewCreated(savedInstanceState: Bundle?) {

    }

    abstract fun getPresenter(): P

    abstract fun getLayoutResource(): Int

    @CallSuper
    public override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }
}