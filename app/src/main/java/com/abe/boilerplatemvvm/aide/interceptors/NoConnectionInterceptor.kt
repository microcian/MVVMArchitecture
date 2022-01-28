package com.abe.boilerplatemvvm.aide.interceptors

import com.abe.boilerplatemvvm.aide.utils.StringUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.io.IOException
import javax.inject.Singleton

@Singleton
class NoConnectionInterceptor(val stringUtils: StringUtils) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected()) throw IOException(stringUtils.noNetworkErrorMessage())
        else return chain.proceed(chain.request())
    }

    private fun isConnected(): Boolean {
        val command = "ping -c 1 www.google.com"
        return Runtime.getRuntime().exec(command).waitFor() == 0
    }
}