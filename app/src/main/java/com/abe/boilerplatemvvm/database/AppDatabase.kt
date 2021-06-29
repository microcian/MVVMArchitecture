package com.abe.boilerplatemvvm.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.abe.boilerplatemvvm.database.converters.PhotoUrlListConverter
import com.abe.boilerplatemvvm.database.converters.StringListConverter
import com.abe.boilerplatemvvm.database.converters.UserConverter
import com.abe.boilerplatemvvm.model.photos.PhotoModel

@Database(entities = [(PhotoModel::class)], version = 1, exportSchema = false)
@TypeConverters(
    value = [(StringListConverter::class),
        (PhotoUrlListConverter::class), (UserConverter::class)]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}
