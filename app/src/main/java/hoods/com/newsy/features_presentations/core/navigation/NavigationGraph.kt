package hoods.com.newsy.features_presentations.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import hoods.com.newsy.features_presentations.detail.DetailScreen
import hoods.com.newsy.features_presentations.headline.HeadlineScreen
import hoods.com.newsy.features_presentations.home.HomeScreen
import hoods.com.newsy.features_presentations.search.SearchScreen
import hoods.com.newsy.utils.K

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

        composable(route = UiScreeen.HeadlineScreen().route) {
            HeadlineScreen (
                onItemClick = {
                    navActions.navigateToDetail(
                        it, UiScreeen.HeadlineScreen().route
                    )
                }
            )
        }

        composable(
            route = UiScreeen.DetailScreen().routeWithArg,
            arguments = listOf(
                navArgument(name = K.articleId) {
                    type = NavType.IntType
                },
                navArgument(name = K.screenType) {
                    type = NavType.StringType
                }
            )
        ){
            DetailScreen(onBack = {
                navController.navigateUp()
            })
        }

        composable(
            route = UiScreeen.SearchScreen().route
        ) {

            SearchScreen(
                onSearchItemClick = {
                     navActions.navigateToDetail(
                         it.id,
                         UiScreeen.SearchScreen().route
                     )
                },
                navigateUp = {
                    navController.navigateUp()
                }
            )

        }
    }


    
}