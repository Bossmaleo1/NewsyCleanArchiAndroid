package hoods.com.newsy.features_presentations.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import hoods.com.newsy.features_presentations.home.HomeScreen

@Composable
fun NewsyNavigationGraph(
    navController: NavHostController = rememberNavController(),
    navActions: NewsyNavigationActions,
    openDrawer: ()-> Unit
) {
    
    NavHost(
        navController = navController,
        startDestination = UiScreeen.HomeScreen().route
    ) {
        composable(route = UiScreeen.HomeScreen().route) {
            HomeScreen(
                onViewMoreClick = navActions.navigateToHeadlineScreen,
                onHeadlineItemClick = {
                       navActions.navigateToDetail(
                           it,UiScreeen.HeadlineScreen().route
                       )
                },
                onDiscoverItemClick = {
                    navActions.navigateToDetail(
                        it, UiScreeen.DiscoverScreen().route
                    )
                },
                onSearchClick = {
                    navActions.navigateToSearch()
                },
                openDrawer = openDrawer
                )
        }
    }
    
}