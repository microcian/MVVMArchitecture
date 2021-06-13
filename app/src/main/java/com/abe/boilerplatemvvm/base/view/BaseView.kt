package com.abe.boilerplatemvvm.base.view

import android.os.Bundle
import androidx.appcompat.widget.AppCompatEditText
import androidx.navigation.NavDirections
import com.abe.boilerplatemvvm.base.stateUI.ErrorDescription

interface BaseView {

    fun getLayoutId(): Int
    fun getNavHostId(): Int?
    fun navigateToDestination(direction: NavDirections)
    fun navigateToDestination(id: Int, args: Bundle)

    fun setSoftInputMode(mode: Int)
    fun resetSoftInputMode()
    fun showKeyboard(editText: AppCompatEditText)
    fun hideKeyboard()

    fun sessionExpire()
    fun onApiError(errorDescription: ErrorDescription)
    fun noConnectivity()
    fun loaderVisibility(visibility: Boolean)
    fun showToast(message: String?)
}