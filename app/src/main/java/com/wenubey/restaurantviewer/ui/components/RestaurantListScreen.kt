package com.wenubey.restaurantviewer.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Navigation
import androidx.compose.material.icons.filled.Restaurant
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.size.Size
import com.wenubey.restaurantviewer.R
import com.wenubey.restaurantviewer.data.local.RestaurantEntity

@Composable
fun RestaurantListScreen(
    paddingValues: PaddingValues,
    restaurants: LazyPagingItems<RestaurantEntity>,
) {
    val lazyRowState = rememberLazyListState()

    LazyRow(
        modifier = Modifier
            .fillMaxSize()
            .padding(paddingValues),
        state = lazyRowState,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        if (restaurants.itemCount > 0) {
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
    val context = LocalContext.current
    val painter = rememberAsyncImagePainter(
        model = ImageRequest.Builder(context)
            .data(restaurant.imageUrl)
            .crossfade(false)
            .size(Size.ORIGINAL)
            .placeholder(R.drawable.ic_block)
            .build()
    )
    Card(
        modifier = Modifier.padding(4.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                modifier = Modifier
                    .size(100.dp)
                    .padding(4.dp),
                painter = painter, contentDescription = "Restaurant image"
            )
            Column(horizontalAlignment = Alignment.Start, modifier = Modifier.padding(4.dp)) {
                CardInfoItem(text = restaurant.name, imageVector = Icons.Default.Restaurant)
                CardInfoItem(text = restaurant.phone, imageVector = Icons.Default.Call)
                CardInfoItem("Distance in meters: ${restaurant.distance.toInt()}", imageVector = Icons.Default.Navigation)
            }
        }
    }
}

@Composable
fun CardInfoItem(
    text: String,
    imageVector: ImageVector,
) {
    Row(verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.spacedBy(4.dp)) {
        Icon(imageVector = imageVector, contentDescription = text)
        Text(text = text)
    }
}