package com.nextbridge.roomdb.converters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nextbridge.roomdb.entities.UserEntityDB

open class UserConverter {
  @TypeConverter
  fun fromString(value: String): UserEntityDB? {
    val listType = object : TypeToken<UserEntityDB>() {}.type
    return Gson().fromJson<UserEntityDB>(value, listType)
  }

  @TypeConverter
  fun fromList(list: UserEntityDB?): String {
    val gson = Gson()
    return gson.toJson(list)
  }
}
