package com.abe.boilerplatemvvm

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import com.abe.boilerplatemvvm.aide.utils.DataStoreUtils
import com.abe.boilerplatemvvm.aide.utils.isNight
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BoilerApp : Application() {

    override fun onCreate() {
        super.onCreate()
        setupDayNightMode()
        DataStoreUtils.init(this)
    }

    private fun setupDayNightMode() {
        // Get UI mode and set
        val mode = if (isNight()) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}
