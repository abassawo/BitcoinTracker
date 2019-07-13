package com.n26.bitcointracker.screens.mainscreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.n26.bitcointracker.BitcoinApp
import com.n26.bitcointracker.R
import com.n26.bitcointracker.base.BaseMvpActivity
import com.n26.bitcointracker.models.Value
import kotlinx.android.synthetic.main.activity_main.*
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

    override fun showChartData(values: List<Value>) {
        val dataValues = mutableListOf<Entry>()
        for (i in 0 until values.size) {
            dataValues.add(
                Entry(
                    values[i].x!!.toFloat(),
                    values[i].y!!.toFloat(),
                    resources.getDrawable(R.drawable.abc_ab_share_pack_mtrl_alpha)
                )
            )
        }


        val dataSets = mutableListOf<ILineDataSet>()
        val lineDataSet = LineDataSet(dataValues, "Dataset 1")
        lineDataSet.values = dataValues

        val lineData = LineData(dataSets)
        chart1.data = lineData
        chart1.notifyDataSetChanged()
    }
}
