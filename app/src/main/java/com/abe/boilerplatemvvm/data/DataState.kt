package com.abe.boilerplatemvvm.data

/**
 * A Sealed class to fetch data from server which will be either data or the error.
 */
sealed class DataState<T> {
    data class Success<T>(val data: T) : DataState<T>()

    //    data class Error<T>(val data: T, val message: String) : DataState<T>()
    data class Error<T>(val message: String) : DataState<T>()

    companion object {
        fun <T> success(data: T) = Success(data)

        //        fun <T> error(data: T, message: String) = Error<T>(data, message)
        fun <T> error(message: String) = Error<T>(message)
    }
}
