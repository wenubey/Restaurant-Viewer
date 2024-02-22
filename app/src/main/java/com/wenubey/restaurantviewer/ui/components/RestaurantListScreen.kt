package com.wenubey.restaurantviewer.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.wenubey.restaurantviewer.data.local.RestaurantEntity

@Composable
fun RestaurantListScreen(
    paddingValues: PaddingValues,
    restaurants: LazyPagingItems<RestaurantEntity>,
) {
    val lazyRowState = rememberLazyListState()

    LazyRow(modifier  = Modifier
        .fillMaxSize()
        .padding(paddingValues),
        state = lazyRowState,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        if(restaurants.itemCount > 0) {
            items(
                count = restaurants.itemCount,
                key = restaurants.itemKey(),
                contentType = restaurants.itemContentType()
            ) { index ->
                val restaurant = restaurants[index]
                if (restaurant != null) {
                    RestaurantListCard(restaurant = restaurant)
                }
            }
        }
    }
}

@Composable
fun RestaurantListCard(
    restaurant: RestaurantEntity
) {
    Card {
        Column {
            Text(text = restaurant.name)
            Text(text = restaurant.phone)
            Text(text = restaurant.categoryAlias.first())
            Text(text = restaurant.categoryTitle.first())
            Text(text = restaurant.distance.toString())
            Text(text = restaurant.rating.toString())
        }
    }
}