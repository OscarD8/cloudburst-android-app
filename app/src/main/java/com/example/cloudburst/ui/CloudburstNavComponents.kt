package com.example.cloudburst.ui

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.cloudburst.model.LocationCategory
import com.example.cloudburst.ui.theme.CloudburstTheme
import com.example.cloudburst.R

@Composable
fun CloudburstNavigationDrawerContent(
    navItems: List<NavigationItem> = navigationItems,
    currentCategory: LocationCategory,
    onTabPressed: (LocationCategory) -> Unit
) {
    navigationItems.forEach { navItem ->
        NavigationDrawerItem(
            label = {
                Text(
                    text = stringResource(navItem.route)
                )
            },
            icon = {
                Icon(
                    painter = painterResource(navItem.icon),
                    contentDescription = stringResource(navItem.route)
                )
            },
            selected = currentCategory == navItem.locationCategory,
            onClick = { onTabPressed(navItem.locationCategory) }
        )
    }
}

@Composable
fun CloudburstNavigationRail(
    currentCategory: LocationCategory,
    onTabPressed: (LocationCategory) -> Unit,
    modifier: Modifier = Modifier,
    navItems: List<NavigationItem> = navigationItems,
) {
    NavigationRail(modifier = modifier) {
        navigationItems.forEach { navItem ->
            NavigationRailItem(
                selected = navItem.locationCategory == currentCategory,
                icon = {
                    Icon(
                        painter = painterResource(navItem.icon),
                        contentDescription = stringResource(navItem.route)
                    )
                },
                onClick = { onTabPressed(navItem.locationCategory) }
            )
        }
    }
}

@Composable
fun CloudburstNavBar(
    currentCategory: LocationCategory,
    onTabPressed: (LocationCategory) -> Unit,
    modifier: Modifier = Modifier,
    navItems: List<NavigationItem> = navigationItems,
) {
    NavigationBar(modifier = modifier) {
        navItems.forEach { navItem ->
            NavigationBarItem(
                selected = currentCategory == navItem.locationCategory,
                onClick = {
                    onTabPressed(navItem.locationCategory)
                },
                icon = {
                    Icon(
                        painter = painterResource(navItem.icon),
                        contentDescription = stringResource(navItem.route)
                    )
                }
            )
        }
    }
}

@Preview
@Composable
fun PreviewNavBar() {
    CloudburstTheme {
        CloudburstNavBar(
            currentCategory = LocationCategory.RESTAURANT, // BOOLEAN FOR HOME?
            onTabPressed = {},
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}