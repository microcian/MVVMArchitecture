package com.abe.boilerplatemvvm.aide.utils

object AppConstants {

    object ApiEndPoints {
        const val BASE_API_URL = "https://api.unsplash.com/"
        const val KEY_PHOTOS = "photos"
        const val KEY_SEARCH_PHOTOS = "search/photos"
    }

    object ApiRequestParams {
        const val PHOTOS_PER_PAGE = 30
        const val API_KEY = "Client-ID wxl8gDYQvKHvTcfTzfFJ2Fy0GoSuKoJovMopdieYBvk"
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
        const val KEY_DEFAULT = "default"
        const val KEY_THEME = "theme"
        const val KEY_LANG = "lang"
    }

    object BundleArgs {
        const val KEY_PHOTO = "photo"
    }

    object CallBack {}
}