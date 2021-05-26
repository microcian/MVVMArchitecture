package com.abe.boilerplatemvvm.base.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.abe.boilerplatemvvm.base.viewmodel.BaseViewModel

abstract class BaseFragment<VB : ViewBinding> : Fragment(), BaseView {

    private var activity: BaseActivity? = null
    private var _bi: VB? = null
    protected val bi: VB get() = _bi!!

    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB
    abstract fun getViewModel(): BaseViewModel?

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            activity = context
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        _bi = bindingInflater(inflater, container, false)
        return _bi!!.root
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

    override fun onDestroyView() {
        super.onDestroyView()
        _bi = null
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