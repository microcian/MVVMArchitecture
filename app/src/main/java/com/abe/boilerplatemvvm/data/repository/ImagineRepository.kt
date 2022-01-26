package com.abe.boilerplatemvvm.data.repository

import com.abe.boilerplatemvvm.data.DataState
import com.nextbridge.roomdb.entities.PhotoEntityDB
import kotlinx.coroutines.flow.Flow

/**
 * ImagineRepository is an interface data layer to handle communication with any data source such as Server or local database.
 * @see [ImagineRepositoryImpl] for implementation of this class to utilize Unsplash API.
 */
interface ImagineRepository {
    suspend fun loadPhotos(pageNumber: Int, pageSize: Int, orderBy: String): Flow<DataState<List<PhotoEntityDB>?>>
    suspend fun searchPhotos(query: String, pageNumber: Int, pageSize: Int): Flow<DataState<List<PhotoEntityDB>?>>
}
