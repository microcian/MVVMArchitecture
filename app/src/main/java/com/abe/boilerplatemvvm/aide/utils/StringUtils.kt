package com.abe.boilerplatemvvm.aide.utils

import android.app.Application
import com.abe.boilerplatemvvm.R

class StringUtils(val appContext: Application) {
    fun noNetworkErrorMessage() = appContext.getString(R.string.message_no_network_connected_str)
    fun somethingWentWrong() = appContext.getString(R.string.message_something_went_wrong_str)
}
