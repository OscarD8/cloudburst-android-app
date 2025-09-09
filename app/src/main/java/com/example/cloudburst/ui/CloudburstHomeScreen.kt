package com.example.cloudburst.ui

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cloudburst.ui.utils.CloudburstContentType
import com.example.cloudburst.ui.utils.CloudburstNavigationType
import com.example.cloudburst.R
import com.example.cloudburst.model.LocationCategory

@Composable
fun CloudburstHome(
    uiState: LocationUiState,
    contentType: CloudburstContentType,
    navigationType: CloudburstNavigationType,
    onTabPressed: (LocationCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    val navigationItems = listOf(
        NavigationItem(
            text = stringResource(R.string.restaurant_header),
            icon = R.drawable.ic_restaurant_placeholder_2,
            locationCategory = LocationCategory.RESTAURANT
        ),
        NavigationItem(
            text = stringResource(R.string.cafe_header),
            icon = R.drawable.ic_cafe_placeholder_1,
            locationCategory = LocationCategory.CAFE
        ),
        NavigationItem(
            text = stringResource(R.string.park_header),
            icon = R.drawable.ic_park_placeholder_3,
            locationCategory = LocationCategory.PARK
        ),
        NavigationItem(
            text = stringResource(R.string.temple_header),
            icon = R.drawable.ic_temple_placeholder_2,
            locationCategory = LocationCategory.TEMPLE
        ),
        NavigationItem(
            text = stringResource(R.string.mycelium_printer_header),
            icon = R.drawable.ic_mycelium_printer_placeholder_3,
            locationCategory = LocationCategory.MYCELIUM_PRINTER
        )
    )

    if (navigationType == CloudburstNavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PermanentNavigationDrawer(
            drawerContent = {
                PermanentDrawerSheet(
                    modifier = Modifier.width(dimensionResource(R.dimen.drawer_width))
                ) {
                    NavigationDrawerContent(
                        navigationItems = navigationItems,
                        currentCategory = uiState.currentLocationCategory,
                        onTabPressed = onTabPressed
                    )
                }
            },
            modifier = modifier
        ) { }
    }

}


@Composable
private fun NavigationDrawerContent(
    navigationItems: List<NavigationItem>,
    currentCategory: LocationCategory,
    onTabPressed: (LocationCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    navigationItems.forEach { navItem ->
        NavigationDrawerItem(
            label = {
                Text(
                    text = navItem.text
                )
            },
            icon = {
                Icon(
                    painter = painterResource(navItem.icon),
                    contentDescription = navItem.text
                )
            },
            selected = currentCategory == navItem.locationCategory,
            onClick = { onTabPressed(navItem.locationCategory) }
        )
    }
}

data class NavigationItem(
    val text: String,
    val locationCategory: LocationCategory,
    @DrawableRes val icon: Int
)