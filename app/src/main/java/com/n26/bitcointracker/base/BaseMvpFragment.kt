package com.n26.bitcointracker.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import io.reactivex.rxkotlin.subscribeBy
import timber.log.Timber

abstract class BaseMvpFragment<V : BaseViewModel>() :
    Fragment() {
    private val viewEventDisposables = CompositeDisposable()
    lateinit var viewModel: V

    protected abstract fun getLayoutResourceId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutResourceId(), container,
        false)

    @CallSuper
    override fun onStart() {
        Timber.v("Lifecycle onStart: $this")
        super.onStart()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = createViewModel()
        viewEventDisposables += viewModel.eventObservable
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(onNext = {
                onViewEvent(it)
            })
        onViewCreated(savedInstanceState)

    }

    abstract fun createViewModel(): V

    /**
     * Override this method to do any additional view initialization (ex: init dagger)
     */
    open fun onViewCreated(@Nullable savedInstanceState: Bundle?) {

    }

    protected open fun onViewEvent(event: ViewEvent) {
    }
}