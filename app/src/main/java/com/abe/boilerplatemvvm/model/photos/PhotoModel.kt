package com.abe.boilerplatemvvm.model.photos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoModel(
    var id: String,
    var created_at: String,
    var color: String,
    var description: String?,
    var urls: PhotoUrlsModel,
    var user: UserModel
) : Parcelable


