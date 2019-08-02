package com.n26.bitcointracker.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

abstract class BaseMvpFragment : Fragment() {

    private lateinit var viewModel: BaseViewModel

    protected abstract fun getLayoutResourceId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutResourceId(), container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(savedInstanceState)
        viewModel = getViewModel()
    }

    abstract fun getViewModel(): BaseViewModel

    /**
     * Override this method to do any additional view initialization (ex: init dagger)
     */
    open fun onViewCreated(@Nullable savedInstanceState: Bundle?) {

    }
}