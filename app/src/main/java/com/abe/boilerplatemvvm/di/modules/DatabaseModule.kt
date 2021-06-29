package com.abe.boilerplatemvvm.di.modules

import android.app.Application
import androidx.room.Room
import com.abe.boilerplatemvvm.aide.utils.AppConstants.PrefKeys.KEY_DATABASE_NAME
import com.abe.boilerplatemvvm.database.AppDatabase
import com.abe.boilerplatemvvm.database.PhotoDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Provides
    @Singleton
    fun providePhotosDB(application: Application): AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, KEY_DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    @Singleton
    fun providePhotoDao(appDatabase: AppDatabase): PhotoDao {
        return appDatabase.photoDao()
    }
}