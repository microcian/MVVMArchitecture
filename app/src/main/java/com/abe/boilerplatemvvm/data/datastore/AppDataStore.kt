package com.abe.boilerplatemvvm.data.datastore

import com.abe.boilerplatemvvm.aide.utils.AppConstants.PrefKeys.KEY_IS_LOGIN
import com.abe.boilerplatemvvm.aide.utils.DataStoreUtils

object AppDataStore {

    var isLogin: Boolean
        get() = DataStoreUtils.readBooleanData(KEY_IS_LOGIN)
        set(b) {
            DataStoreUtils.saveSyncBooleanData(KEY_IS_LOGIN, b)
        }

    fun clear() {
        DataStoreUtils.clearSync()
    }
}