package com.n26.bitcointracker.screens.mainscreen

import com.n26.bitcointracker.base.ViewEvent

sealed class MainViewEvent: ViewEvent {
    data class PageSelected(val index: Int) : MainViewEvent()
}