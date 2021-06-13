package com.abe.boilerplatemvvm.base.viewmodel

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job

abstract class BaseViewModel : ViewModel() {

    companion  object {
        var job = Job()
    }
    var outcomeLiveData = MediatorLiveData<Result<*>>()

    // Cancel the job when the view model is destroyed
    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

//    val error = MutableLiveData<String>()
//    val loader = MutableLiveData<Boolean>()
//
//    private fun showError(e: String) {
//        error.postValue(e)
//    }

//    fun showLoader(show: Boolean) {
//        loader.postValue(show)
//    }

//    fun handleException(exception: Exception) {
//        showLoader(false)
//        when (exception) {
//            is TimeoutException, is SocketTimeoutException -> {
//                showError(exception.toString())
//            }
//            is UnknownHostException -> {
//                showError(exception.toString())
//            }
//            is HttpException -> {
//                showError(exception.toString())
//            }
//            else -> {
//                showError(exception.toString())
//            }
//        }
//    }
}