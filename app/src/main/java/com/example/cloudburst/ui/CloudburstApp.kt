package com.example.cloudburst.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.cloudburst.model.LocationCategory
import com.example.cloudburst.ui.utils.CloudburstContentType
import com.example.cloudburst.ui.utils.CloudburstNavigationType
import com.example.cloudburst.R

enum class CloudburstScreen{
    HOME,
    RESTAURANT,
    CAFE,

}

@Composable
fun CloudburstApp(
    windowSize: WindowWidthSizeClass,
    viewModel: CloudburstViewModel = viewModel(),
    navController: NavHostController = rememberNavController()
) {
    val uiState by viewModel.uiState.collectAsState()
    val contentType: CloudburstContentType
    val navigationType: CloudburstNavigationType

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
        topBar = {}
    ) {
        NavHost(
            navController = navController,
            startDestination = stringResource(R.string.home),
            modifier = Modifier.padding(it)
        ) {
            composable(route = CloudburstScreen.HOME.name) {
                CloudburstHome(
                    uiState = uiState,
                    navigationType = navigationType,
                    contentType = contentType,
                    onTabPressed = {},
                    modifier = Modifier
                        .fillMaxSize()
                )
            }
        }
    }


}
