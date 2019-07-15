package com.n26.bitcointracker.base

import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.rest.RestApi
import com.n26.bitcointracker.utils.rx.SchedulerTransformer
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    val disposables: CompositeDisposable = CompositeDisposable()

    var view: V? = null
    private var isViewBound: Boolean = false
    val ioScheduler: Scheduler = Schedulers.io()
    val uiScheduler: Scheduler = AndroidSchedulers.mainThread()

    @Inject
    lateinit var restApi: RestApi


    override fun bindview(view: V) {
        this.view = view

        if (!isViewBound) {
            onViewBound()
            isViewBound = true
        }
    }

    override fun unbindView() {
        disposables.clear()
        this.view = null

        if (isViewBound) {
            onViewUnbound()
            isViewBound = true
        }
    }

    override fun onViewCreated() {

    }

    override fun onViewDestroyed() {
    }

    private fun onViewUnbound() {
    }

    open fun onViewBound() {

    }

    protected fun <R> subscribeOnIoObserveOnUi(): SchedulerTransformer<R> {
        return SchedulerTransformer(ioScheduler, uiScheduler)
    }

}