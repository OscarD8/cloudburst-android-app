package com.example.cloudburst.ui

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cloudburst.R
import com.example.cloudburst.model.LocationCategory
import com.example.cloudburst.ui.theme.BottomRoundedShape30
import com.example.cloudburst.ui.theme.CloudburstTheme
import com.example.cloudburst.ui.theme.PopUpShape
import com.example.cloudburst.ui.theme.SunriseShape
import com.example.cloudburst.ui.utils.CloudburstContentType
import com.example.cloudburst.ui.utils.CloudburstNavigationType

enum class CloudburstScreen(@StringRes val title: Int){
    HOME(title = R.string.app_name),
    RESTAURANT(title = R.string.restaurant_header),
    CAFE(title = R.string.cafe_header),

}

@Composable
fun CloudburstApp(
    windowSize: WindowWidthSizeClass,
    viewModel: LocationViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val locationUiState by viewModel.uiState.collectAsState()
    val contentType: CloudburstContentType
    val navigationType: CloudburstNavigationType

    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = CloudburstScreen.valueOf(
        backStackEntry?.destination?.route ?: CloudburstScreen.HOME.name
    )

    when (windowSize) {
        WindowWidthSizeClass.Compact -> {
            contentType = CloudburstContentType.LIST_ONLY
            navigationType = CloudburstNavigationType.BOTTOM_NAVBAR
        }
        WindowWidthSizeClass.Medium -> {
            contentType = CloudburstContentType.LIST_ONLY
            navigationType = CloudburstNavigationType.NAVIGATION_RAIL
        }
        WindowWidthSizeClass.Expanded -> {
            contentType = CloudburstContentType.LIST_AND_DETAIL
            navigationType = CloudburstNavigationType.PERMANENT_NAVIGATION_DRAWER
        }
        else -> {
            contentType = CloudburstContentType.LIST_ONLY
            navigationType = CloudburstNavigationType.BOTTOM_NAVBAR
        }
    }

    Scaffold (
        topBar = {
            CloudburstTopAppBar(
                currentScreen = currentScreen
            )
        }
    ) {
        CloudburstAppContent(
            windowSize = windowSize,
            locationUiState = locationUiState,
            navController = navController,
            navigationType = navigationType,
            contentType = contentType,
            onTabPressed = {},
            modifier = Modifier.padding(it)
        )
    }
}

@Composable
fun CloudburstAppContent(
    windowSize: WindowWidthSizeClass,
    locationUiState: LocationUiState,
    navController: NavHostController,
    navigationType: CloudburstNavigationType,
    contentType: CloudburstContentType,
    onTabPressed: (LocationCategory) -> Unit,
    modifier: Modifier = Modifier
) {
    when (navigationType) {
        CloudburstNavigationType.PERMANENT_NAVIGATION_DRAWER -> {
            PermanentNavigationDrawer(
                drawerContent = {
                    PermanentDrawerSheet {
                        CloudburstNavigationDrawerContent(
                            currentCategory = locationUiState.currentLocationCategory,
                            onTabPressed = onTabPressed
                        )
                    }
                }
            ) {
                CloudburstNavHost(
                    navController = navController,
                    windowSize = windowSize,
                    modifier = modifier
                )
            }
        }
        else -> {
            Row(modifier = modifier) {
                AnimatedVisibility(visible = navigationType == CloudburstNavigationType.NAVIGATION_RAIL) {
                    CloudburstNavigationRail(
                        currentCategory = locationUiState.currentLocationCategory,
                        onTabPressed = onTabPressed,
                        modifier = Modifier
                    )
                }
                Column {
                    CloudburstNavHost(
                        navController = navController,
                        windowSize = windowSize,
                        modifier = modifier
                    )
                    AnimatedVisibility(visible = navigationType == CloudburstNavigationType.BOTTOM_NAVBAR) {
                        CloudburstNavBar(
                            currentCategory = locationUiState.currentLocationCategory,
                            onTabPressed = onTabPressed,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CloudburstTopAppBar(
    currentScreen: CloudburstScreen,
    modifier: Modifier = Modifier
) {
    TopAppBar(
        title = {
            Text(
                text = stringResource(currentScreen.title),
                style = MaterialTheme.typography.headlineMedium,
                color = MaterialTheme.colorScheme.onTertiaryContainer
            )
        },
        actions = {
            Box{
                Surface(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(
                            top = dimensionResource(R.dimen.sunrise_padding_top)
                        )
                        .size(
                            width = dimensionResource(R.dimen.sunrise_shape_width),
                            height = dimensionResource(R.dimen.sunrise_shape_height)
                        ),
                    color = MaterialTheme.colorScheme.secondaryContainer,
                    shape = SunriseShape
                ) {
                    Box(
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Surface(
                            color = MaterialTheme.colorScheme.primaryContainer,
                            shape = PopUpShape,
                            modifier = Modifier
                                .size(dimensionResource(R.dimen.inner_circle_size))
                                .align(Alignment.BottomEnd)
                                .padding(
                                    end = dimensionResource(R.dimen.inner_circle_padding_end),
                                    top = dimensionResource(R.dimen.inner_circle_padding_top)
                                )
                        ) {
                        }
                    }
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.tertiaryContainer),
        modifier = Modifier
            .fillMaxWidth()
            .clip(BottomRoundedShape30)
    )
}


@Preview
@Composable
fun PreviewAppBar() {
    CloudburstTheme {
        CloudburstTopAppBar(
            currentScreen = CloudburstScreen.HOME
        )
    }
}