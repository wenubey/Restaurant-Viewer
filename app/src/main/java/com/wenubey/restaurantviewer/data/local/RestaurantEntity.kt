package com.wenubey.restaurantviewer.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = TABLE_NAME)
data class RestaurantEntity(
    @ColumnInfo("id")
    @PrimaryKey
    val id: String,
    @ColumnInfo("image_url")
    val imageUrl: String,
    @ColumnInfo("name")
    val name: String,
    @ColumnInfo("phone")
    val phone: String,
    @ColumnInfo("categoryAlias")
    val categoryAlias: List<String>,
    @ColumnInfo("categoryTitle")
    val categoryTitle: List<String>,
    @ColumnInfo("distance")
    val distance: Double,
    @ColumnInfo("rating")
    val rating: Double,
)



const val TABLE_NAME = "restaurants"
