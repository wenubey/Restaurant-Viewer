package com.wenubey.restaurantviewer.domain

import androidx.paging.PagingData
import com.wenubey.restaurantviewer.data.local.RestaurantEntity
import kotlinx.coroutines.flow.Flow

interface RestaurantRepository {
    fun getRestaurantPagingFlow(): Flow<PagingData<RestaurantEntity>>
}