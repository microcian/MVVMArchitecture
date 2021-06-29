package com.abe.boilerplatemvvm.aide.utils

object AppConstants {

    object ApiEndPoints {
        const val KEY_PHOTOS = "photos"
        const val KEY_SEARCH_PHOTOS = "search/photos"
    }

    object ApiRequestParams {
        const val PHOTOS_PER_PAGE = 30
        const val PARAM_PAGE = "page"
        const val PARAM_PER_PAGE = "per_page"
        const val PARAM_ORDER_BY = "order_by"
        const val PARAM_QUERY = "query"
        const val PARAM_AUTHORIZATION = "Authorization"
    }

    object ResponseParams {
        const val KEY_TOTAL = "total"
        const val KEY_TOTAL_PAGES = "total_pages"
        const val KEY_RESULTS = "results"
    }

    object PrefKeys {
        const val KEY_THEME = "theme"
        const val KEY_LANG = "lang"
        const val KEY_IS_LOGIN = "isLogin"
        const val KEY_PREF_NAME = "AppDataStore"
        const val KEY_DATABASE_NAME = "photos.db"
    }

    object BundleArgs {
        const val KEY_PHOTO = "photo"
    }

    object CallBack {}
}