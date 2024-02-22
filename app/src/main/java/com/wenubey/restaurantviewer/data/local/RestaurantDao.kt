package com.wenubey.restaurantviewer.data.local

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert

@Dao
interface RestaurantDao {

    @Upsert
    suspend fun upsertAll(restaurants: List<RestaurantEntity>)

    @Query("SELECT * FROM restaurants")
    fun pagingSource(): PagingSource<Int, RestaurantEntity>

    @Query("DELETE FROM restaurants")
    suspend fun clearAll()
}