package com.wenubey.restaurantviewer.data.remote

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.wenubey.restaurantviewer.data.remote.dto.RestaurantDto
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

@OptIn(ExperimentalPagingApi::class)
class RestaurantRemoteMediator @Inject constructor(
    private val restaurantApi: RestaurantApi,
    private val locationProvider: RestaurantLocationProvider,
) : RemoteMediator<Int, RestaurantDto>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, RestaurantDto>
    ): MediatorResult {
        try {
            val offset = when (loadType) {
                LoadType.REFRESH -> 1
                LoadType.PREPEND -> return MediatorResult.Success(endOfPaginationReached = true)
                LoadType.APPEND -> {
                    val lastRestaurant = state.lastItemOrNull()
                    if (lastRestaurant == null) {
                        1
                    } else {
                        state.pages.size + 1
                    }
                }
            }
            val location = locationProvider.getUserLocation()
            val longitude = location?.longitude ?: 0.0
            val latitude = location?.latitude ?: 0.0

            val restaurants = restaurantApi.searchRestaurants(
                latitude = latitude,
                longitude = longitude,
                offset = offset
            )
            Log.w(TAG, "loadRestaurants:Success")
            return MediatorResult.Success(endOfPaginationReached = false)
        } catch (e: IOException) {
            Log.e(TAG, "load:Error: ", e)
            return MediatorResult.Error(e)
        } catch (e: HttpException) {
            Log.e(TAG, "load:Error", e)
            return MediatorResult.Error(e)
        }
    }

    companion object {
        const val TAG = "remoteMediator"
    }
}