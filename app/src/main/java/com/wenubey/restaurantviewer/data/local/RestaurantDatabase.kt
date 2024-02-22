package com.wenubey.restaurantviewer.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters


@Database(
    entities = [RestaurantEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(RestaurantTypeConverter::class)
abstract class RestaurantDatabase : RoomDatabase() {
    abstract val dao: RestaurantDao
}