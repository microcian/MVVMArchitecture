package com.abe.boilerplatemvvm.base.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import com.abe.boilerplatemvvm.base.viewmodel.BaseViewModel

abstract class BaseFragment<BINDING : ViewDataBinding> : Fragment(), BaseView {

    abstract fun getViewModel(): BaseViewModel?
    abstract fun initFragment()

    private var activity: BaseActivity<*>? = null
    lateinit var binding: BINDING


    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            activity = context
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return if (!this::binding.isInitialized) {
            DataBindingUtil.inflate<BINDING>(
                    inflater,
                    getLayoutId(),
                    container, false
            ).apply {
                binding = this
                initFragment()
            }.root
        } else binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        hideKeyboard()
        getViewModel()?.let { viewModel ->
            viewModel.loader.observe(viewLifecycleOwner, {
                it?.let {
                    loaderVisibility(it)
                }
            })
            viewModel.error.observe(viewLifecycleOwner, {
                it?.let {
                    showToast(it)
                }
            })
        }
    }

    override fun setSoftInputMode(mode: Int) {
        activity?.setSoftInputMode(mode)
    }

    override fun resetSoftInputMode() {
        activity?.resetSoftInputMode()
    }

    override fun showKeyboard(editText: EditText) {
        activity?.showKeyboard(editText)
    }

    override fun hideKeyboard() {
        activity?.hideKeyboard()
    }

    override fun sessionExpire() {
        activity?.sessionExpire()
    }

    override fun noConnectivity() {
        activity?.noConnectivity()
    }

    override fun loaderVisibility(visibility: Boolean) {
        activity?.loaderVisibility(visibility)
    }

    override fun showToast(message: String?) {
        activity?.showToast(message)
    }

    fun callBackKeyHandling(function: () -> Unit) {
        val callback: OnBackPressedCallback =
                object : OnBackPressedCallback(true) {
                    override fun handleOnBackPressed() {
                        function()
                    }
                }
        activity?.onBackPressedDispatcher?.addCallback(this, callback)
    }
}