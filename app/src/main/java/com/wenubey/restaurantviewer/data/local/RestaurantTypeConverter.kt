package com.wenubey.restaurantviewer.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class RestaurantTypeConverter {

    private inline fun <reified T> fromJson(json: String?, typeToken: TypeToken<out T>): T? {
        return if (json == null) null else Gson().fromJson(json, typeToken.type)
    }

    private fun <T> toJson(data: T?): String? {
        return data?.let { Gson().toJson(it) }
    }

    @TypeConverter
    fun listFromJson(json: String?): List<String>? =
        fromJson(json, object : TypeToken<List<String>?>() {})

    @TypeConverter
    fun listToJson(list: List<String>): String? =
        toJson(list)
}