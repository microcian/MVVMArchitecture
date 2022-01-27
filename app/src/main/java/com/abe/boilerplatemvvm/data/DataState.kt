package com.abe.boilerplatemvvm.data

import com.abe.boilerplatemvvm.model.error.ErrorModel

/**
 * A Sealed class to fetch data from server which will be either data or the error.
 */
sealed class DataState<T> {
    data class Success<T>(val data: T) : DataState<T>()

    data class Error<T>(val data: T, val errorModel: ErrorModel) : DataState<T>()

    companion object {
        fun <T> success(data: T) = Success(data)

        fun <T> error(data: T, errorModel: ErrorModel) = Error(data, errorModel)
    }
}
