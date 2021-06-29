package com.abe.boilerplatemvvm.database.converters

import androidx.room.TypeConverter
import com.abe.boilerplatemvvm.model.photos.UserModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

open class UserConverter {
  @TypeConverter
  fun fromString(value: String): UserModel? {
    val listType = object : TypeToken<UserModel>() {}.type
    return Gson().fromJson<UserModel>(value, listType)
  }

  @TypeConverter
  fun fromList(list: UserModel?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}
