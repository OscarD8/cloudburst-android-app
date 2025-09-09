package com.example.cloudburst.ui

import com.example.cloudburst.model.Location
import com.example.cloudburst.model.LocationCategory

data class LocationUiState(
    val locationCategories: Map<LocationCategory, List<Location>> = emptyMap(),
    val currentLocationCategory: LocationCategory = LocationCategory.RESTAURANT,
    val currentSelectedLocation: Location? = locationCategories[currentLocationCategory]?.first(),
    val isShowingHomePage: Boolean = false
) {
    val currentLocationCategoryItems: List<Location> by lazy { locationCategories[currentLocationCategory]!! }
}