package com.n26.bitcointracker.base

import com.n26.bitcointracker.utils.rx.SchedulerTransformer
import io.reactivex.Scheduler
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

abstract class BasePresenter<V : BaseContract.View> : BaseContract.Presenter<V> {

    val disposables: CompositeDisposable = CompositeDisposable()

    private var view: V? = null
    private var isViewBound: Boolean = false
    @Inject lateinit var ioScheduler: Scheduler
    @Inject lateinit var uiScheduler: Scheduler

    init {
        //dagger
    }

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

    private fun onViewUnbound() {
    }

    private fun onViewBound() {
    }

    protected fun <R> subscribeOnIoObserveOnUi(): SchedulerTransformer<R> {
        //todo  this can be provided by dagger too
        return SchedulerTransformer(ioScheduler, uiScheduler)
    }

}