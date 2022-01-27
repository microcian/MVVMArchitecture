package com.abe.boilerplatemvvm.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.abe.boilerplatemvvm.model.error.Errors

open class BaseViewModel : ViewModel() {

    var baseUiState = MutableLiveData<BaseUiState>()
    var inLoadingState = false

    fun showError(error: Errors? = null) {
        if(error == null)
            baseUiState.postValue(ErrorState())
//        when (error.errorCode) {
//            SESSION_EXPIRE -> baseUiState.postValue(SessionExpire(error.message))
//            SUBSCRIPTION_EXPIRED -> baseUiState.postValue(SubscriptionExpired(error.message))
//            else -> baseUiState.postValue(ErrorState(error.message))
//        }
    }

    fun showLoader() {
        baseUiState.postValue(LoadingState)
    }

    fun showLoaderPagination(page: Int) {
        baseUiState.postValue(if (page == 1) LoadingState else LoadingNextPageState)
    }

    fun hideLoader() {
        baseUiState.postValue(DismissLoaderState)
    }

    fun handleException(exception: Exception) {
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
    }
}