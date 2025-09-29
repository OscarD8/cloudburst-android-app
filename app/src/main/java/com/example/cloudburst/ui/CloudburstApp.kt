package com.example.cloudburst.ui

import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices.PIXEL_9
import androidx.compose.ui.tooling.preview.Devices.TABLET
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.cloudburst.R
import com.example.cloudburst.model.LocationCategory
import com.example.cloudburst.ui.theme.BottomRoundedShape30
import com.example.cloudburst.ui.theme.CloudburstTheme
import com.example.cloudburst.ui.theme.PopUpShape
import com.example.cloudburst.ui.theme.RightSideRoundedShape30
import com.example.cloudburst.ui.theme.SunriseShape
import com.example.cloudburst.ui.theme.shadowCustom
import com.example.cloudburst.ui.utils.CloudburstContentType
import com.example.cloudburst.ui.utils.CloudburstNavigationType

/**
 * Defines the primary screens in the Cloudburst application.
 * Each screen is associated with a string resource that serves as its title.
 */
enum class CloudburstScreen(@StringRes val title: Int){
    HOME(title = R.string.app_name),
    RESTAURANT(title = R.string.restaurant_header),
    CAFE(title = R.string.cafe_header),

}

/**
 * The root composable for the Cloudburst application.
 *
 * This function acts as the main entry point for the UI. It is responsible for:
 * 1.  Determining the appropriate navigation type (`BOTTOM_NAVBAR`, `NAVIGATION_RAIL`, `PERMANENT_NAVIGATION_DRAWER`)
 * and content type (`LIST_ONLY`, `LIST_AND_DETAIL`) based on the device's [windowSize].
 * 2.  Setting up the main `Scaffold` which includes the top app bar and a conditional bottom navigation bar.
 * 3.  Displaying a consistent background across the entire app.
 * 4.  Delegating the primary content layout to [CloudburstAppContent].
 *
 * @param windowSize The size class of the window, used to create adaptive layouts.
 * @param viewModel The ViewModel providing UI state for location data.
 * @param navController The controller for navigating between screens.
 */
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

    Box{
        CloudburstBackground(modifier = Modifier.fillMaxSize())

        Scaffold(
            topBar = {
                CloudburstTopAppBar(
                    currentScreen = currentScreen
                )
            },
            bottomBar = {
                AnimatedVisibility(visible = navigationType == CloudburstNavigationType.BOTTOM_NAVBAR) {
                    CloudburstNavBar(
                        currentCategory = locationUiState.currentLocationCategory,
                        onTabPressed = {  },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            },
            containerColor = Color.Transparent
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
}

/**
 * Renders the main content area of the app based on the selected navigation type.
 *
 * This composable arranges the primary UI elements, such as the navigation rail or
 * permanent drawer, alongside the main content host ([CloudburstNavHost]).
 *
 * @param windowSize The current window size class.
 * @param locationUiState The state object from the ViewModel.
 * @param navController The navigation controller.
 * @param navigationType The determined navigation strategy (e.g., Rail, Drawer).
 * @param contentType The determined content strategy (e.g., List only).
 * @param onTabPressed A lambda function to handle navigation item presses.
 * @param modifier A [Modifier] to be applied to the root layout.
 */
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
                    PermanentDrawerSheet (
                        drawerContainerColor = MaterialTheme.colorScheme.primaryContainer,
                        drawerShape = RightSideRoundedShape30,
                    ) {
                        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.navdrawer_spacer_height)))
                        CloudburstNavigationDrawerContent(
                            currentCategory = locationUiState.currentLocationCategory,
                            onTabPressed = onTabPressed,
                            modifier = Modifier.padding(dimensionResource(R.dimen.navdrawer_item_horizontal_padding))
                        )
                    }
                },
                modifier = Modifier
                    .width(dimensionResource(R.dimen.drawer_width))
                    .shadowCustom(
                        offsetY = dimensionResource(R.dimen.shadow_offset_negative_y),
                        blurRadius = dimensionResource(R.dimen.shadow_blur_radius),
                        shapeRadius = dimensionResource(R.dimen.shadow_shape_radius),
                    )
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
                Column(
                    modifier = Modifier.weight(1f),
                ) {
                    CloudburstNavHost(
                        navController = navController,
                        windowSize = windowSize,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
        }
    }
}

/**
 * A custom, styled top app bar for the Cloudburst application.
 *
 * Displays the current screen's title and a unique decorative "sunrise" element on the right.
 * It features a custom shadow and a clipped shape for a distinct visual identity.
 *
 * @param currentScreen The [CloudburstScreen] enum for the currently displayed screen.
 * @param modifier A [Modifier] to be applied to the app bar.
 */
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
            .shadowCustom(
                offsetY = dimensionResource(id = R.dimen.shadow_offset_y),
                blurRadius = dimensionResource(id = R.dimen.shadow_blur_radius),
                shapeRadius = dimensionResource(id = R.dimen.shadow_shape_radius)
            )
            .clip(BottomRoundedShape30)
    )
}

/**
 * A consistent background composable for the entire app.
 *
 * It displays a static background image that fills the screen. It intelligently applies
 * a semi-transparent dark overlay when the system's dark theme is active to ensure
 * content readability and improve aesthetics. ✨
 *
 * @param modifier A [Modifier] to be applied to the background container.
 */
@Composable
private fun CloudburstBackground(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
            Image(
                painter = painterResource(R.drawable.cloudburst_bg),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize()
            )

        if (isSystemInDarkTheme()) {
            Surface (
                color = Color.Black.copy(alpha = 0.8f),
                modifier = Modifier.fillMaxSize()
            ) {}
        }
    }
}




//@Preview
//@Composable
//fun PreviewAppBar() {
//    CloudburstTheme {
//        CloudburstTopAppBar(
//            currentScreen = CloudburstScreen.HOME
//        )
//    }
//}

@Preview (device = PIXEL_9, name = "Compact Home")
@Composable
fun PreviewCompactHome() {
    CloudburstTheme {
        CloudburstApp(
            windowSize = WindowWidthSizeClass.Compact,
        )
    }
}

//@Preview (device = TABLET, name = "Expanded Home Light")
//@Composable
//fun PreviewExpandedHomeLight() {
//    CloudburstTheme {
//        CloudburstApp(
//            windowSize = WindowWidthSizeClass.Expanded
//        )
//    }
//}

//@Preview (device = TABLET, name = "Expanded Home Dark")
//@Composable
//fun PreviewExpandedHomeDark() {
//    CloudburstTheme(darkTheme = true) {
//        CloudburstApp(
//            windowSize = WindowWidthSizeClass.Expanded
//        )
//    }
//}

//@Preview
//@Composable
//fun PreviewBg(){
//    CloudburstTheme {
//        CloudburstBackground(modifier = Modifier.fillMaxSize())
//    }
//}