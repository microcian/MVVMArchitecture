package com.abe.boilerplatemvvm.base.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatEditText
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.NavDirections
import com.abe.boilerplatemvvm.base.stateUI.StateViewModel
import com.abe.boilerplatemvvm.base.viewmodel.BaseViewModel

abstract class BaseFragment<BINDING : ViewDataBinding> : Fragment(), BaseView {

    private var activity: BaseActivity<*>? = null
    lateinit var binding: BINDING

    abstract fun initFragment()
    abstract fun getViewModel(): BaseViewModel?

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
//        getViewModel()?.let { viewModel ->
//            viewModel.loader.observe(viewLifecycleOwner, Observer {
//                it?.let {
//                    loaderVisibility(it)
//                }
//            })
//            viewModel.error.observe(viewLifecycleOwner, Observer {
//                it?.let {
//                    showToast(it)
//                }
//            })
//        }
        getViewModel()?.outcomeLiveData?.observe(viewLifecycleOwner, Observer {
            when (it) {
                is StateViewModel.Loading -> {
                    if (it.showLoader)
                        loaderVisibility(true)
                }
                is StateViewModel.Success -> {
                    loaderVisibility(false)
                }
                is StateViewModel.Failure -> {
                    onApiError(it.e!!)
                    loaderVisibility(false)
                }
                is StateViewModel.NetworkError -> {
                    onApiError(it.e!!)
                    loaderVisibility(false)
                }
            }

        })
    }


    override fun sessionExpire() {
        activity?.sessionExpire()
    }

    override fun setSoftInputMode(mode: Int) {
        activity?.setSoftInputMode(mode)
    }

    override fun resetSoftInputMode() {
        activity?.resetSoftInputMode()
    }

    override fun showKeyboard(editText: AppCompatEditText) {
        activity?.showKeyboard(editText)
    }

    override fun hideKeyboard() {
        activity?.hideKeyboard()
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

    override fun onApiError(errorDescription: ErrorDescription) {
        activity?.onApiError(errorDescription)
    }

    override fun navigateToDestination(direction: NavDirections) {
        activity?.navigateToDestination(direction)
    }

    override fun navigateToDestination(id: Int, args: Bundle) {
        activity?.navigateToDestination(id, args)
    }

    override fun getNavHostId(): Int? {
        return activity?.getNavHostId()
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