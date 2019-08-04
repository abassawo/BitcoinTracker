package com.n26.bitcointracker.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy

abstract class BaseMvvmActivity<V : BaseViewModel> : AppCompatActivity(),
    BaseContract.View {
    private val viewEventDisposables = CompositeDisposable()
    protected lateinit var viewModel: V


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResource())
        viewModel = createViewModel()
        onViewCreated(savedInstanceState)
    }

    /**
     * Override this method to do any additional view initialization (ex: init dagger)
     */
    protected open fun onViewCreated(savedInstanceState: Bundle?) {

    }

    override fun onStart() {
        super.onStart()
        viewEventDisposables += viewModel.eventObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                onViewEvent(it)
            })
    }

    protected open fun onViewEvent(event: ViewEvent) {
    }

    abstract fun createViewModel(): V

    abstract fun getLayoutResource(): Int
}