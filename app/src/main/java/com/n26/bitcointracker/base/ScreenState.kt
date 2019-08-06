package com.n26.bitcointracker.base

interface IScreenState

sealed class ScreenState : IScreenState {
    data class Error(val errorMsg: Throwable) : ScreenState()
}