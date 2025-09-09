package com.example.cloudburst.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes


data class Location (
    @StringRes val name: Int,
    @StringRes val address: Int,
    val rating: Int,
    val isCarbonCapturing: Boolean,
    @DrawableRes val imageResId: Int,
    val category: LocationCategory,
    @StringRes val description: Int
)