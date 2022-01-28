package com.abe.boilerplatemvvm.data.remote

/**
 * A suspend function for handling success response.
 */
@SuspensionFunction
suspend fun <T> ApiResponse<T>.onSuccessSuspend(
    onResult: suspend ApiResponse.ApiSuccessResponse<T>.() -> Unit
): ApiResponse<T> {
    if (this is ApiResponse.ApiSuccessResponse) onResult(this)
    return this
}

/**
 * A suspend function for handling error response.
 */
@SuspensionFunction
suspend fun <T> ApiResponse<T>.onErrorSuspend(
    onResult: suspend ApiResponse.ApiFailureResponse.Error<T>.() -> Unit
): ApiResponse<T> {
    if (this is ApiResponse.ApiFailureResponse.Error) onResult(this)
    return this
}

/**
 * A suspend function for handling exception response.
 */
@SuspensionFunction
suspend fun <T> ApiResponse<T>.onExceptionSuspend(
    onResult: suspend ApiResponse.ApiFailureResponse.Exception<T>.() -> Unit
): ApiResponse<T> {
    if (this is ApiResponse.ApiFailureResponse.Exception) onResult(this)
    return this
}