package com.example.cloudburst.ui

import androidx.lifecycle.ViewModel
import com.example.cloudburst.data.LocalLocationsDataProvider
import com.example.cloudburst.model.Location
import com.example.cloudburst.model.LocationCategory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class CloudburstViewModel: ViewModel() {
    private var _uiState = MutableStateFlow(LocationUiState())
    val uiState: StateFlow<LocationUiState> = _uiState.asStateFlow()

    init {
        initLocationCategoryUiState()
    }

    private fun initLocationCategoryUiState() {
        val locationCategories: Map<LocationCategory, List<Location>> =
            LocalLocationsDataProvider.allLocations.groupBy { it.category }

        _uiState.value = LocationUiState(
            locationCategories = locationCategories,
            currentSelectedLocation = locationCategories[LocationCategory.RESTAURANT]?.firstOrNull()
        )
    }
}