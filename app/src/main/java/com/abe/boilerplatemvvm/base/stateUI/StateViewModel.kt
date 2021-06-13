package com.abe.boilerplatemvvm.base.stateUI


sealed class StateViewModel<T> {

    class Loading<T>(var showLoader: Boolean = false) : StateViewModel<T>()
    class Success<T>(var data: T) : StateViewModel<T>()
    class Failure<E>(val e: ErrorDescription?) : StateViewModel<E>()
    class NetworkError<E>(val e: ErrorDescription?) : StateViewModel<E>()

}