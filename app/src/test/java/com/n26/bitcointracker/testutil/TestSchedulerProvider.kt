package com.n26.bitcointracker.testutil

import com.n26.bitcointracker.di.SchedulerProvider
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider : SchedulerProvider {

    val testScheduler: TestScheduler = TestScheduler()

    override fun uiScheduler() = testScheduler
    override fun ioScheduler() = testScheduler
}