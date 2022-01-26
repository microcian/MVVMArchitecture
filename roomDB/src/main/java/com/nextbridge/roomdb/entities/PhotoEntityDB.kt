package com.nextbridge.roomdb.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class PhotoEntityDB(
    @PrimaryKey var id: String,
    var created_at: String? = null,
    var color: String? = null,
    var description: String? = null,
    var urls: PhotoUrlsEntityDB? = null,
    var user: UserEntityDB? = null
) : Parcelable


