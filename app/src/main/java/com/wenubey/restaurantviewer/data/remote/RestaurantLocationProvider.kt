package com.wenubey.restaurantviewer.data.remote

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.util.Log
import androidx.core.content.ContextCompat
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RestaurantLocationProvider
@Inject constructor(
    private val fusedLocationProviderClient: FusedLocationProviderClient,
    @ApplicationContext private val context: Context,
) {
    private val priority =
        CurrentLocationRequest.Builder().setPriority(Priority.PRIORITY_BALANCED_POWER_ACCURACY)
            .build()

    fun getUserLocation(): Location? {
        return if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            try {
                fusedLocationProviderClient.getCurrentLocation(
                    priority,
                    null
                )
                    .addOnFailureListener {
                        Log.e(TAG, "getUserLocation: Error", it)
                    }
                    .addOnSuccessListener { location ->
                        Log.d(TAG, "getUserLocation: Success - $location")
                    }
                    .result
            } catch (e: Exception) {
                Log.e(TAG, "getUserLocation: Error", e)
                null
            }
        } else {
            Log.e(TAG, "getUserLocation: Location permission not granted")
            null
        }
    }

    companion object {
        const val TAG = "restaurantLocationProvider"
    }
}