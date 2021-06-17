package com.abe.boilerplatemvvm.model.photos

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class PhotoModel(
    @PrimaryKey var id: String,
    var created_at: String,
    var color: String,
    var description: String,
    var urls: PhotoUrlsModel,
    var user: UserModel
) : Parcelable


