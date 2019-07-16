package com.n26.bitcointracker.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment

abstract class BaseMvpFragment<P : BaseContract.Presenter<*>> : Fragment() {

    private lateinit var presenter: P

    protected abstract fun getLayoutResourceId(): Int
    abstract fun getPresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = inflater.inflate(getLayoutResourceId(), container, false);

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewCreated(savedInstanceState);
        presenter = getPresenter()
    }

    /**
     * Override this method to do any additional view initialization (ex: init dagger)
     */
    open fun onViewCreated(@Nullable savedInstanceState: Bundle?) {

    }

}