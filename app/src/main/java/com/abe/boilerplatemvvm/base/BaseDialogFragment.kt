package com.abe.boilerplatemvvm.base

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.*
import android.widget.EditText
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.DialogFragment

abstract class BaseDialogFragment<BINDING : ViewDataBinding> : DialogFragment(), BaseView {

    private var activity: BaseActivity<*>? = null
    lateinit var binding: BINDING

    abstract fun getViewModel(): BaseViewModel?
    abstract fun initFragment()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        if (null != dialog.window) dialog.window!!.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return if (!this::binding.isInitialized) {
            DataBindingUtil.inflate<BINDING>(
                inflater,
                getLayoutId(),
                container, false
            ).apply {
                binding = this
                initFragment()
                if (null != dialog && null != dialog!!.window) {
                    dialog!!.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
                    dialog!!.window!!.setGravity(Gravity.CENTER)
                }
            }.root
        } else binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        liveDataObservations()
    }

    override fun onStart() {
        super.onStart()
        val dialog = dialog
        if (null != dialog) {
            val window = dialog.window
            if (null != window) dialog.window!!
                .setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            activity = context
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

    override fun noConnectivity() {
        activity?.noConnectivity()
    }

    override fun loaderVisibility(visibility: Boolean) {
        activity?.loaderVisibility(visibility)
    }

    override fun showToast(message: String?) {
        activity?.showToast(message)
    }

    override fun getLayoutId(): Int {
        TODO("Not yet implemented")
    }

    private fun liveDataObservations() {
        getViewModel()?.let { viewModel ->
            viewModel.baseUiState.observe(viewLifecycleOwner) { state ->
                when (state) {
                    is LoadingState -> loaderVisibility(true)
                    is ErrorState -> loaderVisibility(false)
                    else -> loaderVisibility(false)
                }
            }
        }
    }
}