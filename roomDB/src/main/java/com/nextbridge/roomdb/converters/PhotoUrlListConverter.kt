package com.nextbridge.roomdb.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nextbridge.roomdb.entities.PhotoUrlsEntityDB

open class PhotoUrlListConverter {
  @TypeConverter
  fun fromString(value: String): PhotoUrlsEntityDB? {
    val listType = object : TypeToken<PhotoUrlsEntityDB>() {}.type
    return Gson().fromJson<PhotoUrlsEntityDB>(value, listType)
  }

  @TypeConverter
  fun fromList(list: PhotoUrlsEntityDB?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}
