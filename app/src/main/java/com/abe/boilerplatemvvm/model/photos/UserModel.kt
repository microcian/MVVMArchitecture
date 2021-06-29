package com.abe.boilerplatemvvm.model.photos

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class UserModel(
    var id: String,
    var username: String,
    var name: String
) : Parcelable
