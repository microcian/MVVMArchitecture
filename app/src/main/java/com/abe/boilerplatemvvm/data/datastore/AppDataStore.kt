package com.abe.boilerplatemvvm.data.datastore

import com.abe.boilerplatemvvm.aide.utils.AppConstants.PrefKeys.KEY_IS_LOGIN
import com.abe.boilerplatemvvm.aide.utils.DataStoreUtils

class AppDataStore {

    companion object {
        val dataStore = DataStoreUtils

        var isLogin: Boolean
            get() = dataStore.readBooleanData(KEY_IS_LOGIN)
            set(b) {
                dataStore.saveSyncBooleanData(KEY_IS_LOGIN, b)
            }

        fun clear() {
            dataStore.clearSync()
        }
    }
}