package com.nextbridge.roomdb.entities

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class PhotoUrlsEntityDB(
    var raw: String? = null,
    var full: String? = null,
    var regular: String? = null,
    var small: String? = null,
    var thumb: String? = null
) : Parcelable