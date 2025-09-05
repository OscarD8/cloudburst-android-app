package com.example.cloudburst.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

enum class Category{
    RESTAURANT, CAFE, PARK, TEMPLE, MYCELLIUM_PRINTER
}
data class Location (
    @StringRes val name: Int,
    @StringRes val address: Int,
    val rating: Int,
    val isCarbonCapturing: Boolean,
    @DrawableRes val imageResId: Int,
    val category: Category,
    @StringRes val description: Int
)