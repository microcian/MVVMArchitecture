package com.abe.boilerplatemvvm.base

import android.widget.EditText

interface BaseView {
    fun getLayoutId(): Int
    fun setSoftInputMode(mode: Int)
    fun resetSoftInputMode()
    fun showKeyboard(editText: EditText)
    fun hideKeyboard()
    fun sessionExpire()
    fun noConnectivity()
    fun loaderVisibility(visibility: Boolean)
    fun showToast(message: String?)
}