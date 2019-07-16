package com.n26.bitcointracker.base

import com.n26.bitcointracker.rest.AppRepository
import com.n26.bitcointracker.settings.UserSettings
import io.reactivex.disposables.CompositeDisposable

abstract class BasePresenter<V : BaseContract.View>(
    val userSettings: UserSettings,
    val appRepository: AppRepository
) : BaseContract.Presenter<V> {

    val disposables: CompositeDisposable = CompositeDisposable()
    var view: V? = null
    private var isViewBound: Boolean = false

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

    override fun onViewDestroyed() {
    }

    private fun onViewUnbound() {
    }

    open fun onViewBound() {

    }
}