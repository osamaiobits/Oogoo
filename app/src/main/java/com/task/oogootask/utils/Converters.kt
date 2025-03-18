package com.task.oogootask.utils

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.task.oogootask.ui.data.CarImages

class Converters {

    private val gson = Gson()

    @TypeConverter
    fun fromCarImageList(images: List<CarImages>): String {
        return gson.toJson(images) // Convert List<CarImage> to JSON String
    }

    @TypeConverter
    fun toCarImageList(data: String): List<CarImages> {
        val listType = object : TypeToken<List<CarImages>>() {}.type
        return gson.fromJson(data, listType) // Convert JSON String back to List<CarImage>
    }
}
