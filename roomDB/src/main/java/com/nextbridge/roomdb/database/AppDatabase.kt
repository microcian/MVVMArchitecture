package com.nextbridge.roomdb.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nextbridge.roomdb.converters.PhotoUrlListConverter
import com.nextbridge.roomdb.converters.StringListConverter
import com.nextbridge.roomdb.converters.UserConverter
import com.nextbridge.roomdb.entities.PhotoEntityDB

@Database(entities = [(PhotoEntityDB::class)], version = 1, exportSchema = false)
@TypeConverters(
    value = [(StringListConverter::class),
        (PhotoUrlListConverter::class), (UserConverter::class)]
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun photoDao(): PhotoDao
}
