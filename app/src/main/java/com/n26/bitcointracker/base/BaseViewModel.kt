package com.n26.bitcointracker.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.n26.bitcointracker.rest.AppRepository
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import javax.inject.Inject


abstract class BaseViewModel(appContext: Application) : AbstractViewModel(appContext) {
    @Inject
    lateinit var appRepository: AppRepository
    protected val disposables = CompositeDisposable()
    private val eventPublisher: PublishSubject<ViewEvent> = PublishSubject.create()
    val eventObservable: Observable<ViewEvent> = eventPublisher

    protected fun sendViewEvent(viewEvent: ViewEvent) = eventPublisher.onNext(viewEvent)

    // Works exactly the same way as MutableLiveData.value
    // This allows all the subclasses' live data to be declared as LiveData<T> type instead of MutableLiveData<T> so
    // that their values can't be changed externally but still can internally
    // see https://github.com/IntrepidPursuits/skotlinton-android/pull/33#discussion_r275908063
    protected var <T : Any> LiveData<T>.latestValue: T?
        get() = this.value
        set(value) {
            (this as MutableLiveData<T>).value = value
        }

}

abstract class AbstractViewModel(appContext: Application) : AndroidViewModel(appContext)

interface ViewEvent