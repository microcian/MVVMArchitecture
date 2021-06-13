package com.abe.boilerplatemvvm.base.repo

import androidx.lifecycle.*
import com.abe.boilerplatemvvm.base.stateUI.ErrorDescription
import com.abe.boilerplatemvvm.base.stateUI.StateViewModel
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

abstract class BaseRepository<Response, Params> internal constructor() {

    abstract suspend fun fetchFromNetwork(params: Params): Response


    open fun start(
        params: Params,
        job: Job

    ): LiveData<StateViewModel<out Any?>> = liveData(Dispatchers.IO + job) {
        emit(StateViewModel.Loading(true))
        try {
            emit(StateViewModel.Success(fetchFromNetwork(params)))
        } catch (e: HttpException) {
            emit(StateViewModel.NetworkError<Error>(ErrorDescription(e.localizedMessage!!)))

        } catch (e: UnknownHostException) {
            emit(StateViewModel.NetworkError<Error>(ErrorDescription(e.localizedMessage!!)))
        } catch (e: SocketTimeoutException) {
            emit(StateViewModel.NetworkError<Error>(ErrorDescription(e.localizedMessage!!)))
        } catch (ex: Exception) {
            emit(StateViewModel.Failure<Error>(ErrorDescription(ex.localizedMessage!!)))
        }
    }
}