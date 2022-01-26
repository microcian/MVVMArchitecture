package com.abe.boilerplatemvvm.data.remote.response.search

import com.abe.boilerplatemvvm.aide.utils.AppConstants.ResponseParams.KEY_RESULTS
import com.abe.boilerplatemvvm.aide.utils.AppConstants.ResponseParams.KEY_TOTAL
import com.abe.boilerplatemvvm.aide.utils.AppConstants.ResponseParams.KEY_TOTAL_PAGES
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.nextbridge.roomdb.entities.PhotoEntityDB

data class SearchPhotosResponse(
    @Expose @SerializedName(KEY_TOTAL) val total: Int,
    @Expose @SerializedName(KEY_TOTAL_PAGES) val totalPages: Int,
    @Expose @SerializedName(KEY_RESULTS) val photosList: List<PhotoEntityDB>? = null
)
