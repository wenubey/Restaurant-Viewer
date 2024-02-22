package com.wenubey.restaurantviewer.data.remote

import android.annotation.SuppressLint
import android.content.Context
import android.location.Location
import android.util.Log
import com.google.android.gms.location.FusedLocationProviderClient
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RestaurantLocationProvider
@Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
) {

    @SuppressLint("MissingPermission")
    fun getUserLocation(): Location? {
        return try {
            return fusedLocationProviderClient.lastLocation
                .addOnFailureListener {
                    Log.e(TAG, "getUserLocation:Error: ", it)
                }
                .addOnSuccessListener {
                    Log.w(TAG, "getUserLocation:Success:'$it'", )
                }
                .result
        } catch (e: Exception) {
            Log.e(TAG, "getUserLocation:Error", e)
            null
        }
    }

    companion object {
        const val TAG = "restaurantLocationProvider"
    }
}