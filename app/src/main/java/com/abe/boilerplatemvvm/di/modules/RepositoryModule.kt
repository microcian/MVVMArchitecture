package com.abe.boilerplatemvvm.di.modules

import android.app.Application
import com.abe.boilerplatemvvm.aide.utils.StringUtils
import com.abe.boilerplatemvvm.data.remote.ApiService
import com.abe.boilerplatemvvm.data.repository.ImagineRepository
import com.abe.boilerplatemvvm.data.repository.ImagineRepositoryImpl
import com.nextbridge.roomdb.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * The Dagger Module for providing repository instances.
 */
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun provideStringUtils(app: Application): StringUtils {
        return StringUtils(app)
    }

    @Singleton
    @Provides
    fun provideImagineRepository(
        stringUtils: StringUtils,
        apiService: ApiService,
        appDatabase: AppDatabase
    ): ImagineRepository {
        return ImagineRepositoryImpl(stringUtils, apiService, appDatabase)
    }
}
