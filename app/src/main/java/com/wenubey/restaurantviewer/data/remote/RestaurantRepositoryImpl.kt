package com.wenubey.restaurantviewer.data.remote

import androidx.paging.Pager
import androidx.paging.PagingData
import com.wenubey.restaurantviewer.data.local.RestaurantEntity
import com.wenubey.restaurantviewer.domain.RestaurantRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RestaurantRepositoryImpl @Inject constructor(
    private val pager: Pager<Int, RestaurantEntity>
): RestaurantRepository {

    override fun getRestaurantPagingFlow(): Flow<PagingData<RestaurantEntity>> = pager.flow
}