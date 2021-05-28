package com.abe.boilerplatemvvm.data.remote

import com.abe.boilerplatemvvm.aide.utils.StringUtils
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit
import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type

class ApiResponseCallAdapterFactory(
        private val stringUtils: StringUtils
) : CallAdapter.Factory() {

    override fun get(
            returnType: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
    ) = when (getRawType(returnType)) {
        Call::class.java -> {
            val callType = getParameterUpperBound(0, returnType as ParameterizedType)
            when (getRawType(callType)) {
                ApiResponse::class.java -> {
                    val responseType = getParameterUpperBound(0, callType as ParameterizedType)
                    ApiResponseCallAdapter(responseType, stringUtils)
                }
                else -> null
            }
        }
        else -> null
    }
}
