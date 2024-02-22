package com.wenubey.restaurantviewer.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RestaurantListDto(
    @SerializedName("total")
    val total: Int,
    @SerializedName("businesses")
    val restaurantDto: List<RestaurantDto>,

    )


