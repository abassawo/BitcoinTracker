package com.n26.bitcointracker.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import com.n26.bitcointracker.R
import com.n26.bitcointracker.models.Range
import kotlinx.android.synthetic.main.range_label_item.view.*

class PillAdapter(val items: List<Range>, val listener: OnPillClickListener) : RecyclerView.Adapter<PillAdapter.PillViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PillViewHolder(inflateView(parent, PillViewHolder.layoutResource), listener)

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PillViewHolder, position: Int) =
        holder.bind(items[position])

    fun inflateView(viewGroup: ViewGroup, layoutResource: Int): View {
        with(LayoutInflater.from(viewGroup.context)) {
            return inflate(layoutResource, viewGroup, false)
        }
    }

    class PillViewHolder(view: View, val listener: OnPillClickListener) : RecyclerView.ViewHolder(view), View.OnClickListener {
        var range: Range? = null

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(range: Range) {
            this.range = range
            itemView.title.text = range.timeSpan
        }

        override fun onClick(v: View?) {
            val selectedRange = range ?: Range.ALL
            listener.onPillClicked(selectedRange)
        }

        companion object {
            @LayoutRes
            val layoutResource = R.layout.range_label_item
        }
    }

    interface OnPillClickListener {
        fun onPillClicked(range: Range)
    }
}