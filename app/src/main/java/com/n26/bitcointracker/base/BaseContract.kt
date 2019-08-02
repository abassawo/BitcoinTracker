package com.n26.bitcointracker.base

interface BaseContract {

    interface View

//    interface Presenter<V : View> {
//        fun bindView(view: V)
//        fun unbindView()
//        fun onViewDestroyed()
//    }

    interface ViewModel<V : View>
}