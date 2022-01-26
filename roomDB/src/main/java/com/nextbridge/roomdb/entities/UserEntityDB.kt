package com.nextbridge.roomdb.entities

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity
data class UserEntityDB(
    var id: String? = null,
    var username: String? = null,
    var name: String? = null
) : Parcelable
