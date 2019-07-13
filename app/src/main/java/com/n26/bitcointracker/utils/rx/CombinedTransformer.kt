package com.n26.bitcointracker.utils.rx

import io.reactivex.*

interface CombinedTransformer<R> : ObservableTransformer<R, R>, FlowableTransformer<R, R>,
    SingleTransformer<R, R>, CompletableTransformer, MaybeTransformer<R, R>