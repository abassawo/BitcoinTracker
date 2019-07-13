package com.n26.bitcointracker.screens.mainscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.R
import com.n26.bitcointracker.base.BaseMvpActivity
import javax.inject.Inject

class MainActivity : BaseMvpActivity<MainContract.Presenter>(), MainContract.View {
    @Inject
    lateinit var presenter: MainPresenter

    override fun getPresenter(): MainContract.Presenter {
        BitcoinApp.instance.baseLibComponent?.inject(this)
        return presenter
    }

    override fun onResume() {
        super.onResume()
        presenter.bindview(this)
    }

    override fun getLayoutResource(): Int = R.layout.activity_main
}
