package com.wenubey.restaurantviewer.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.wenubey.restaurantviewer.domain.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RestaurantViewModel @Inject constructor(
    private val restaurantRepository: RestaurantRepository,
) : ViewModel() {

    val restaurantPagingFlow =
        restaurantRepository.getRestaurantPagingFlow()
            .cachedIn(viewModelScope)
}