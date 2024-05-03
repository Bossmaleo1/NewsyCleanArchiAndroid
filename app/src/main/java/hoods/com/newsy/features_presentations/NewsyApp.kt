package hoods.com.newsy.features_presentations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.rememberNavController
import hoods.com.newsy.features_presentations.core.navigation.NewsyNavigationActions
import hoods.com.newsy.features_presentations.core.navigation.NewsyNavigationGraph

@Composable
fun NewsyApp() {
    val navController = rememberNavController()
    val navActions = remember {
        NewsyNavigationActions(navController)
    }

    NewsyNavigationGraph(
        navActions =  navActions,
        navController = navController,
        openDrawer = {}
        )
}