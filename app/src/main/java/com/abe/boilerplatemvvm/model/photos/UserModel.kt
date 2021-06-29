package com.abe.boilerplatemvvm.model.photos

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UserModel(
    var id: String,
    var username: String,
    var name: String
) : Parcelable
