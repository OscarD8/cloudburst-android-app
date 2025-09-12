package com.example.cloudburst.ui

import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationRail
import androidx.compose.material3.NavigationRailItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.example.cloudburst.ui.utils.CloudburstContentType
import com.example.cloudburst.ui.utils.CloudburstNavigationType
import com.example.cloudburst.R
import com.example.cloudburst.model.LocationCategory

@Composable
fun CloudburstHomeScreen(
//    locationUiState: LocationUiState,
    windowSize: WindowWidthSizeClass,
//    contentType: CloudburstContentType,
    modifier: Modifier = Modifier
) {
    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            HomeScreenCompact()
        }
        WindowWidthSizeClass.Medium -> {
            HomeScreenMedium()
        }
        WindowWidthSizeClass.Expanded -> {
            HomeScreenExpanded()
        } else -> {
            HomeScreenCompact()
        }
    }
}

@Composable
internal fun HomeScreenCompact(
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        Image(
            painter = painterResource(R.drawable.home_bg_portrait),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}

@Composable
internal fun HomeScreenMedium() {

}

@Composable
internal fun HomeScreenExpanded() {

}