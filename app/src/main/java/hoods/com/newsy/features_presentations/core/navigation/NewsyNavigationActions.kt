package hoods.com.newsy.features_presentations.core.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination

class NewsyNavigationActions(
    navController: NavController
) {
    val navigateToHome: () -> Unit = {
        navController.navigateToSingleTop(
            UiScreeen.HomeScreen().route
        )
    }
    val navigateToDetail: (
            id: Int,
            screenType: String
            ) -> Unit = {id, screenType ->
                navController.navigateToSingleTop(
                    route = "${UiScreeen.DetailScreen().route}/$id&$screenType"
                )
            }
    val navigateToHeadlineScreen: () -> Unit = {
        navController.navigateToSingleTop(
            UiScreeen.HeadlineScreen().route
        )
    }

    val navigateToSettingsScreen: () -> Unit = {
        navController.navigateToSingleTop(
            UiScreeen.SettingsScreen().route
        )
    }

    val navigateToFavouriteScreen: () -> Unit = {
        navController.navigateToSingleTop(
            UiScreeen.FavouriteScreen().route
        )
    }

    val navigateToSearch: () -> Unit = {
        navController.navigateToSingleTop(
            UiScreeen.SearchScreen().route
        )
    }
}

fun NavController.navigateToSingleTop(route: String) {
    navigate(route) {
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        launchSingleTop = true
        restoreState = true
    }
}