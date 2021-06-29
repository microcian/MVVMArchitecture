package com.abe.boilerplatemvvm.model.photos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class PhotoUrlsModel(
    var raw: String,
    var full: String,
    var regular: String,
    var small: String,
    var thumb: String
) : Parcelable