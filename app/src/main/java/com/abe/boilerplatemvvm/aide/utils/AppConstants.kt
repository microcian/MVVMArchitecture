package com.abe.boilerplatemvvm.aide.utils

object AppConstants {

    const val CONNECT_TIMEOUT: Long = 30
    const val WRITE_TIMEOUT: Long = 30
    const val READ_TIMEOUT: Long = 30
    const val BEARER: String = "Bearer "

    object API {
        val PHOTOS_PER_PAGE = 30
        val API_KEY = "Client-ID wxl8gDYQvKHvTcfTzfFJ2Fy0GoSuKoJovMopdieYBvk"
    }

    object PrefKeys {
        const val KEY_DEFAULT = "default"
        const val KEY_THEME = "theme"
        const val KEY_LANG = "lang"
    }
}