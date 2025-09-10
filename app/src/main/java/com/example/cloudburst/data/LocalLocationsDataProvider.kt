package com.example.cloudburst.data

import com.example.cloudburst.R
import com.example.cloudburst.model.Location
import com.example.cloudburst.model.LocationCategory

object LocalLocationsDataProvider {

    val allRestaurants = listOf(
        Location(R.string.restaurant_name_1, R.string.restaurant_address_1, rating = 5, isCarbonCapturing = true,  R.drawable.ic_restaurant_placeholder_1, LocationCategory.RESTAURANT, R.string.restaurant_description_1),
        Location(R.string.restaurant_name_2, R.string.restaurant_address_2, rating = 4, isCarbonCapturing = false, R.drawable.ic_restaurant_placeholder_2, LocationCategory.RESTAURANT, R.string.restaurant_description_2),
        Location(R.string.restaurant_name_3, R.string.restaurant_address_3, rating = 5, isCarbonCapturing = true,  R.drawable.ic_restaurant_placeholder_3, LocationCategory.RESTAURANT, R.string.restaurant_description_3),
        Location(R.string.restaurant_name_4, R.string.restaurant_address_4, rating = 3, isCarbonCapturing = false, R.drawable.ic_restaurant_placeholder_2, LocationCategory.RESTAURANT, R.string.restaurant_description_4),
        Location(R.string.restaurant_name_5, R.string.restaurant_address_5, rating = 5, isCarbonCapturing = true,  R.drawable.ic_restaurant_placeholder_1, LocationCategory.RESTAURANT, R.string.restaurant_description_5),
        Location(R.string.restaurant_name_6, R.string.restaurant_address_6, rating = 4, isCarbonCapturing = true,  R.drawable.ic_restaurant_placeholder_3, LocationCategory.RESTAURANT, R.string.restaurant_description_6)
    )

    val allCafes = listOf(
        Location(R.string.cafe_name_1, R.string.cafe_address_1, rating = 5, isCarbonCapturing = true,  R.drawable.ic_cafe_placeholder_1, LocationCategory.CAFE, R.string.cafe_description_1),
        Location(R.string.cafe_name_2, R.string.cafe_address_2, rating = 4, isCarbonCapturing = false, R.drawable.ic_cafe_placeholder_2, LocationCategory.CAFE, R.string.cafe_description_2),
        Location(R.string.cafe_name_3, R.string.cafe_address_3, rating = 5, isCarbonCapturing = true,  R.drawable.ic_cafe_placeholder_3, LocationCategory.CAFE, R.string.cafe_description_3),
        Location(R.string.cafe_name_4, R.string.cafe_address_4, rating = 3, isCarbonCapturing = true,  R.drawable.ic_cafe_placeholder_2, LocationCategory.CAFE, R.string.cafe_description_4),
        Location(R.string.cafe_name_5, R.string.cafe_address_5, rating = 5, isCarbonCapturing = false, R.drawable.ic_cafe_placeholder_1, LocationCategory.CAFE, R.string.cafe_description_5),
        Location(R.string.cafe_name_6, R.string.cafe_address_6, rating = 4, isCarbonCapturing = true,  R.drawable.ic_cafe_placeholder_3, LocationCategory.CAFE, R.string.cafe_description_6)
    )

    val allParks = listOf(
        Location(R.string.park_name_1, R.string.park_address_1, rating = 5, isCarbonCapturing = true,  R.drawable.ic_park_placeholder_1, LocationCategory.PARK, R.string.park_description_1),
        Location(R.string.park_name_2, R.string.park_address_2, rating = 4, isCarbonCapturing = false, R.drawable.ic_park_placeholder_2, LocationCategory.PARK, R.string.park_description_2),
        Location(R.string.park_name_3, R.string.park_address_3, rating = 5, isCarbonCapturing = true,  R.drawable.ic_park_placeholder_3, LocationCategory.PARK, R.string.park_description_3),
        Location(R.string.park_name_4, R.string.park_address_4, rating = 4, isCarbonCapturing = true,  R.drawable.ic_park_placeholder_2, LocationCategory.PARK, R.string.park_description_4),
        Location(R.string.park_name_5, R.string.park_address_5, rating = 5, isCarbonCapturing = false, R.drawable.ic_park_placeholder_1, LocationCategory.PARK, R.string.park_description_5),
        Location(R.string.park_name_6, R.string.park_address_6, rating = 5, isCarbonCapturing = true,  R.drawable.ic_park_placeholder_3, LocationCategory.PARK, R.string.park_description_6)
    )

    val allTemples = listOf(
        Location(R.string.temple_name_1, R.string.temple_address_1, rating = 5, isCarbonCapturing = false, R.drawable.ic_temple_placeholder_1, LocationCategory.TEMPLE, R.string.temple_description_1),
        Location(R.string.temple_name_2, R.string.temple_address_2, rating = 4, isCarbonCapturing = false, R.drawable.ic_temple_placeholder_2, LocationCategory.TEMPLE, R.string.temple_description_2),
        Location(R.string.temple_name_3, R.string.temple_address_3, rating = 5, isCarbonCapturing = false, R.drawable.ic_temple_placeholder_3, LocationCategory.TEMPLE, R.string.temple_description_3),
        Location(R.string.temple_name_4, R.string.temple_address_4, rating = 4, isCarbonCapturing = false, R.drawable.ic_temple_placeholder_2, LocationCategory.TEMPLE, R.string.temple_description_4),
        Location(R.string.temple_name_5, R.string.temple_address_5, rating = 5, isCarbonCapturing = false, R.drawable.ic_temple_placeholder_1, LocationCategory.TEMPLE, R.string.temple_description_5),
        Location(R.string.temple_name_6, R.string.temple_address_6, rating = 4, isCarbonCapturing = false, R.drawable.ic_temple_placeholder_3, LocationCategory.TEMPLE, R.string.temple_description_6)
    )

    val allMyceliumPrinters = listOf(
        Location(R.string.printer_name_1, R.string.printer_address_1, rating = 5, isCarbonCapturing = true,  R.drawable.ic_mycelium_printer_placeholder_1, LocationCategory.MYCELIUM_PRINTER, R.string.printer_description_1),
        Location(R.string.printer_name_2, R.string.printer_address_2, rating = 4, isCarbonCapturing = true,  R.drawable.ic_mycelium_printer_placeholder_2, LocationCategory.MYCELIUM_PRINTER, R.string.printer_description_2),
        Location(R.string.printer_name_3, R.string.printer_address_3, rating = 5, isCarbonCapturing = false, R.drawable.ic_mycelium_printer_placeholder_3, LocationCategory.MYCELIUM_PRINTER, R.string.printer_description_3),
        Location(R.string.printer_name_4, R.string.printer_address_4, rating = 4, isCarbonCapturing = true,  R.drawable.ic_mycelium_printer_placeholder_1, LocationCategory.MYCELIUM_PRINTER, R.string.printer_description_4),
        Location(R.string.printer_name_5, R.string.printer_address_5, rating = 5, isCarbonCapturing = true,  R.drawable.ic_mycelium_printer_placeholder_2, LocationCategory.MYCELIUM_PRINTER, R.string.printer_description_5),
        Location(R.string.printer_name_6, R.string.printer_address_6, rating = 4, isCarbonCapturing = false, R.drawable.ic_mycelium_printer_placeholder_1, LocationCategory.MYCELIUM_PRINTER, R.string.printer_description_6)
    )

    val allLocations = allRestaurants + allCafes + allParks + allTemples + allMyceliumPrinters
}
