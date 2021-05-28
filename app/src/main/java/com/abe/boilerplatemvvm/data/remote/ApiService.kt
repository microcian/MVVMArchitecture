package com.abe.boilerplatemvvm.data.remote

import com.abe.boilerplatemvvm.aide.utils.AppConstants.ApiEndPoints.KEY_PHOTOS
import com.abe.boilerplatemvvm.aide.utils.AppConstants.ApiEndPoints.KEY_SEARCH_PHOTOS
import com.abe.boilerplatemvvm.aide.utils.AppConstants.ApiRequestParams.PARAM_ORDER_BY
import com.abe.boilerplatemvvm.aide.utils.AppConstants.ApiRequestParams.PARAM_PAGE
import com.abe.boilerplatemvvm.aide.utils.AppConstants.ApiRequestParams.PARAM_PER_PAGE
import com.abe.boilerplatemvvm.aide.utils.AppConstants.ApiRequestParams.PARAM_QUERY
import com.abe.boilerplatemvvm.data.remote.response.search.SearchPhotosResponse
import com.abe.boilerplatemvvm.model.photos.PhotoModel
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(KEY_PHOTOS)
    suspend fun loadPhotos(
        @Query(PARAM_PAGE) page: Int = 1,
        @Query(PARAM_PER_PAGE) numOfPhotos: Int = 10,
        @Query(PARAM_ORDER_BY) orderBy: String = "popular"
    ): ApiResponse<List<PhotoModel>>

    @GET(KEY_SEARCH_PHOTOS)
    suspend fun searchPhotos(
        @Query(PARAM_QUERY) query: String,
        @Query(PARAM_PAGE) page: Int = 1,
        @Query(PARAM_PER_PAGE) numOfPhotos: Int = 10,
    ): ApiResponse<SearchPhotosResponse>
}
