package com.abe.boilerplatemvvm.model.error

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Errors(
    var errors: List<String>
) : Parcelable
