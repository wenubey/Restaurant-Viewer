package com.wenubey.restaurantviewer.data.remote


import com.wenubey.restaurantviewer.data.remote.dto.RestaurantListDto
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface RestaurantApi {


    @GET("businesses/search")
    suspend fun searchRestaurants(
        @Header("Authorization") authHeader: String = API_KEY,
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,
        @Query("offset") offset: Int,
    ): RestaurantListDto

    companion object {
        const val BASE_URL = "https://api.yelp.com/v3/"
        const val API_KEY = "Bearer PgTnCEUY8j4Sa-BnywseGSGxdaNxQFjcjsg2cNK-myJVgArqOEMZqKbRYypGAMdgzIvloosBS9EjYMbpMQ0hNr4mxSn7oWinMineVFMof0zyKONoJLPmp6L1lWnUZXYx"
    }

}