package com.wenubey.restaurantviewer.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.wenubey.restaurantviewer.data.local.RestaurantEntity

data class RestaurantDto(
    @SerializedName("id")
    val id: String,
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
) {
    fun mapToRestaurantEntity(): RestaurantEntity {
        return RestaurantEntity(
            imageUrl = imageUrl,
            name = name,
            phone = phone,
            categoryAlias = categoryDto.map { it.alias },
            categoryTitle = categoryDto.map { it.title },
            distance = distance,
            rating = rating,
            id = id
        )
    }
}

data class CategoryDto(
    @SerializedName("alias")
    val alias: String,
    @SerializedName("title")
    val title: String
)