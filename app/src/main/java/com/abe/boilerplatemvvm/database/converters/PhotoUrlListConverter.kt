package com.abe.boilerplatemvvm.database.converters

import androidx.room.TypeConverter
import com.abe.boilerplatemvvm.model.photos.PhotoUrlsModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class PhotoUrlListConverter {
  @TypeConverter
  fun fromString(value: String): PhotoUrlsModel? {
    val listType = object : TypeToken<PhotoUrlsModel>() {}.type
    return Gson().fromJson<PhotoUrlsModel>(value, listType)
  }

  @TypeConverter
  fun fromList(list: PhotoUrlsModel?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}
