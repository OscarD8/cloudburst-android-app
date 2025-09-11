package com.example.cloudburst.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.ui.res.stringResource
import com.example.cloudburst.R
import com.example.cloudburst.model.LocationCategory

data class NavigationItem(
    @StringRes val route: Int,
    val locationCategory: LocationCategory,
    @DrawableRes val icon: Int
)

val navigationItems = listOf(
    NavigationItem(
        route = R.string.restaurant_header,
        icon = R.drawable.ic_restaurant_placeholder_2,
        locationCategory = LocationCategory.RESTAURANT
    ),
    NavigationItem(
        route = R.string.cafe_header,
        icon = R.drawable.ic_cafe_placeholder_1,
        locationCategory = LocationCategory.CAFE
    ),
    NavigationItem(
        route = R.string.park_header,
        icon = R.drawable.ic_park_placeholder_3,
        locationCategory = LocationCategory.PARK
    ),
    NavigationItem(
        route = R.string.temple_header,
        icon = R.drawable.ic_temple_placeholder_2,
        locationCategory = LocationCategory.TEMPLE
    ),
    NavigationItem(
        route = R.string.mycelium_printer_header,
        icon = R.drawable.ic_mycelium_printer_placeholder_3,
        locationCategory = LocationCategory.MYCELIUM_PRINTER
    )
)



