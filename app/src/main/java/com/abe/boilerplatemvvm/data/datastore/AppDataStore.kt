package com.abe.boilerplatemvvm.data.datastore

import com.abe.boilerplatemvvm.aide.utils.AppConstants.PrefKeys.KEY_IS_LOGIN
import com.abe.boilerplatemvvm.aide.utils.AppConstants.PrefKeys.KEY_LANG
import com.abe.boilerplatemvvm.aide.utils.AppConstants.PrefKeys.KEY_THEME
import com.abe.boilerplatemvvm.aide.utils.DataStoreUtils

object AppDataStore {

    var isLogin: Boolean
        get() = DataStoreUtils.readBooleanData(KEY_IS_LOGIN)
        set(b) {
            DataStoreUtils.saveSyncBooleanData(KEY_IS_LOGIN, b)
        }

    var appLanguage: String
        get() = DataStoreUtils.readStringData(KEY_LANG)
        set(b) {
            DataStoreUtils.saveSyncStringData(KEY_LANG, b)
        }

    var uiMode: Int
        get() = DataStoreUtils.readIntData(KEY_THEME)
        set(b) {
            DataStoreUtils.saveSyncIntData(KEY_THEME, b)
        }

    fun clear() {
        DataStoreUtils.clearSync()
    }
}