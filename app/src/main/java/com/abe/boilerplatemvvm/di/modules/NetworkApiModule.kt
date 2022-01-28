package com.abe.boilerplatemvvm.di.modules

import com.abe.boilerplatemvvm.BuildConfig
import com.abe.boilerplatemvvm.aide.interceptors.NoConnectionInterceptor
import com.abe.boilerplatemvvm.aide.utils.AppConstants.ApiRequestParams.PARAM_AUTHORIZATION
import com.abe.boilerplatemvvm.aide.utils.StringUtils
import com.abe.boilerplatemvvm.data.remote.ApiResponseCallAdapterFactory
import com.abe.boilerplatemvvm.data.remote.ApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

/**
 * The Dagger Module to provide the instances of [OkHttpClient], [Retrofit], and [ApiService] classes.
 */
@Module
@InstallIn(SingletonComponent::class)
class NetworkApiModule {

    @Singleton
    @Inject
    @Provides
    fun providesOkHttpClient(stringUtils: StringUtils): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE

        return OkHttpClient.Builder()
            .addInterceptor { chain ->
                val request = chain.request()
                val newRequest =
                    request.newBuilder().header(PARAM_AUTHORIZATION, BuildConfig.API_KEY)
                chain.proceed(newRequest.build())
            }
            .addInterceptor(logging)
            .addInterceptor(NoConnectionInterceptor(stringUtils))
            .build()
    }

    @Singleton
    @Provides
    fun providesRetrofit(
        okHttpClient: OkHttpClient,
        stringUtils: StringUtils
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory(stringUtils))
            .build()
    }

    @Singleton
    @Provides
    fun providesApiService(retrofit: Retrofit): ApiService {
        return retrofit.create(ApiService::class.java)
    }
}
