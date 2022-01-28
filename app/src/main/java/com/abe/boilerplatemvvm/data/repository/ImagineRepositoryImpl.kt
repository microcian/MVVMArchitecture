package com.abe.boilerplatemvvm.data.repository

import androidx.annotation.WorkerThread
import com.abe.boilerplatemvvm.aide.utils.StringUtils
import com.abe.boilerplatemvvm.data.DataState
import com.abe.boilerplatemvvm.data.remote.ApiService
import com.abe.boilerplatemvvm.data.remote.onErrorSuspend
import com.abe.boilerplatemvvm.data.remote.onExceptionSuspend
import com.abe.boilerplatemvvm.data.remote.onSuccessSuspend
import com.nextbridge.roomdb.database.AppDatabase
import com.nextbridge.roomdb.entities.PhotoEntityDB
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * This is an implementation of [ImagineRepository] to handle communication with [ApiService] server.
 */
class ImagineRepositoryImpl @Inject constructor(
    private val stringUtils: StringUtils,
    private val apiService: ApiService,
    private val appDatabase: AppDatabase
) : ImagineRepository {

    @WorkerThread
    override suspend fun loadPhotos(
        pageNumber: Int,
        pageSize: Int,
        orderBy: String
    ): Flow<DataState<List<PhotoEntityDB>?>> {
        return flow {
            apiService.loadPhotos(pageNumber, pageSize, orderBy).apply {
                this.onSuccessSuspend {
                    data?.let {
                        appDatabase.photoDao().insertPhotosList(it)
                        emit(DataState.success(it))
                    }
                }
            }.onErrorSuspend {
                emit(
                    DataState.error(
                        appDatabase.photoDao().getAllPhotos(),
                        errorModel!!
                    )
                )

                // handle the case when the API request gets an exception response.
                // e.g. network connection error.
            }.onExceptionSuspend {
                emit(
                    DataState.error(
                        appDatabase.photoDao().getAllPhotos(),
                        errorModel()
                    )
                )
            }
        }
    }

    override suspend fun searchPhotos(
        query: String,
        pageNumber: Int,
        pageSize: Int
    ): Flow<DataState<List<PhotoEntityDB>?>> {
        return flow {
            apiService.searchPhotos(query, pageNumber, pageSize).apply {
                this.onSuccessSuspend {
                    data?.let {
                        emit(DataState.success(it.photosList))
                    }
                }
                // handle the case when the API request gets an error response.
                // e.g. internal server error.
            }.onErrorSuspend {
                emit(
                    DataState.error(
                        appDatabase.photoDao().getAllPhotos(),
                        errorModel!!
                    )
                )
            }.onExceptionSuspend {
                emit(
                    DataState.error(
                        appDatabase.photoDao().getAllPhotos(),
                        errorModel()
                    )
                )
            }
        }
    }
}
