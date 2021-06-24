package com.abe.boilerplatemvvm.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

open class BaseViewModel : ViewModel() {

    val error = MutableLiveData<String>()
    val loader = MutableLiveData<Boolean>()

    fun getLoadingValue(): Boolean {
        return loader.value?.let {
            loader.value
        } ?: false
    }

    private fun showError(e: String) {
        error.postValue(e)
    }

    fun showLoader(show: Boolean) {
        loader.postValue(show)
    }

    fun handleException(exception: Exception) {
        showLoader(false)
        when (exception) {
            is TimeoutException, is SocketTimeoutException -> {
                showError(exception.toString())
            }
            is UnknownHostException -> {
                showError(exception.toString())
            }
            is HttpException -> {
                showError(exception.toString())
            }
            else -> {
                showError(exception.toString())
            }
        }
    }
}