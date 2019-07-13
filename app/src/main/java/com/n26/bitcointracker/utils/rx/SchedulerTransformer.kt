package com.n26.bitcointracker.utils.rx

import androidx.annotation.NonNull
import io.reactivex.*
import org.reactivestreams.Publisher

class SchedulerTransformer<R>(private val ioScheduler: Scheduler, private val uiScheduler: Scheduler
) : CombinedTransformer<R> {

    @NonNull
    override fun apply(@NonNull upstream: Completable): CompletableSource {
        return upstream.subscribeOn(ioScheduler).observeOn(uiScheduler)
    }

    @NonNull
    override fun apply(@NonNull upstream: Flowable<R>): Publisher<R> {
        return upstream.subscribeOn(ioScheduler).observeOn(uiScheduler)
    }

    @NonNull
    override fun apply(@NonNull upstream: Maybe<R>): MaybeSource<R> {
        return upstream.subscribeOn(ioScheduler).observeOn(uiScheduler)
    }

    @NonNull
    override fun apply(@NonNull upstream: Observable<R>): ObservableSource<R> {
        return upstream.subscribeOn(ioScheduler).observeOn(uiScheduler)
    }

    @NonNull
    override fun apply(@NonNull upstream: Single<R>): SingleSource<R> {
        return upstream.subscribeOn(ioScheduler).observeOn(uiScheduler)
    }
}
