package com.wenubey.restaurantviewer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RestaurantDto(
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("phone")
    val phone: String,
    @SerializedName("categories")
    val categoryDto: List<CategoryDto>,
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("rating")
    val rating: Double,
)

data class CategoryDto(
    @SerializedName("alias")
    val alias: String,
    @SerializedName("title")
    val title: String
)