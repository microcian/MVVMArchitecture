package com.abe.boilerplatemvvm.data.remote.response.search

import com.abe.boilerplatemvvm.aide.utils.AppConstants.ResponseParams.KEY_RESULTS
import com.abe.boilerplatemvvm.aide.utils.AppConstants.ResponseParams.KEY_TOTAL
import com.abe.boilerplatemvvm.aide.utils.AppConstants.ResponseParams.KEY_TOTAL_PAGES
import com.abe.boilerplatemvvm.model.photos.PhotoModel
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SearchPhotosResponse(
    @Expose @SerializedName(KEY_TOTAL) val total: Int,
    @Expose @SerializedName(KEY_TOTAL_PAGES) val totalPages: Int,
    @Expose @SerializedName(KEY_RESULTS) val photosList: List<PhotoModel>
)
