package com.wenubey.restaurantviewer.di

import android.content.Context
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.room.Room
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.wenubey.restaurantviewer.data.local.RestaurantDao
import com.wenubey.restaurantviewer.data.local.RestaurantDatabase
import com.wenubey.restaurantviewer.data.local.RestaurantEntity
import com.wenubey.restaurantviewer.data.remote.RestaurantApi
import com.wenubey.restaurantviewer.data.remote.RestaurantLocationProvider
import com.wenubey.restaurantviewer.data.remote.RestaurantRemoteMediator
import com.wenubey.restaurantviewer.data.remote.RestaurantRepositoryImpl
import com.wenubey.restaurantviewer.data.remote.dto.RestaurantDto
import com.wenubey.restaurantviewer.domain.RestaurantRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRestaurantDatabase(@ApplicationContext context: Context): RestaurantDatabase {
        return Room.databaseBuilder(
            context,
            RestaurantDatabase::class.java,
            "restaurants.db"
        ).build()
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
        return client.build()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
        gsonConverterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): RestaurantApi {
        return Retrofit.Builder()
            .baseUrl(RestaurantApi.BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .client(okHttpClient)
            .build()
            .create(RestaurantApi::class.java)
    }

    @Singleton
    @Provides
    fun provideFusedLocationProviderClient(@ApplicationContext context: Context): FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @OptIn(ExperimentalPagingApi::class)
    @Singleton
    @Provides
    fun provideRestaurantPager(
        dao: RestaurantDao,
        api: RestaurantApi,
        locationProvider: RestaurantLocationProvider
    ): Pager<Int,RestaurantEntity> {
        return Pager(
            config = PagingConfig(pageSize = 20),
            remoteMediator = RestaurantRemoteMediator(restaurantApi = api, locationProvider = locationProvider, dao = dao),
            pagingSourceFactory = { dao.pagingSource() }
        )
    }

    @Singleton
    @Provides
    fun provideConverterFactory(): GsonConverterFactory = GsonConverterFactory.create()

    @Singleton
    @Provides
    fun provideRestaurantDao(db: RestaurantDatabase): RestaurantDao = db.dao

    @Singleton
    @Provides
    fun provideRestaurantRepository(restaurantPager: Pager<Int, RestaurantEntity>): RestaurantRepository {
        return RestaurantRepositoryImpl(pager = restaurantPager)
    }
}