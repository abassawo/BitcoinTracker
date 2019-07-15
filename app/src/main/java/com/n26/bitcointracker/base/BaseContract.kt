package com.n26.bitcointracker.base

interface BaseContract {

    interface View  {

    }

    interface Presenter<V : View> {
        fun bindview(view: V)
        fun unbindView()
        fun onViewDestroyed()

    }
}