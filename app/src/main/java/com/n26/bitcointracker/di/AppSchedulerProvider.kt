package com.n26.bitcointracker.di

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider : SchedulerProvider {
    override fun ioScheduler() = Schedulers.io()
    override fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
}

interface SchedulerProvider {
    fun uiScheduler() : Scheduler
    fun ioScheduler() : Scheduler
}