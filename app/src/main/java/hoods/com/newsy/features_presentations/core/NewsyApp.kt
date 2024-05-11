package hoods.com.newsy.features_presentations.core

import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import hoods.com.newsy.features_presentations.core.navigation.NewsyAppDrawerContent
import hoods.com.newsy.features_presentations.core.navigation.NewsyNavigationActions
import hoods.com.newsy.features_presentations.core.navigation.NewsyNavigationGraph
import hoods.com.newsy.features_presentations.core.navigation.UiScreeen
import kotlinx.coroutines.launch

@Composable
fun NewsyApp() {
    val navController = rememberNavController()
    val navActions = remember {
        NewsyNavigationActions(navController)
    }


    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute =
        navBackStackEntry?.destination?.route ?: UiScreeen.HomeScreen().route
    val scope = rememberCoroutineScope()

    ModalNavigationDrawer(
        drawerContent = {
            NewsyAppDrawerContent(
                currentRoute = currentRoute,
                navigateToHome = navActions.navigateToHome,
                navigateToSetting = navActions.navigateToSettingsScreen,
                navigateToFavouriteScreen = navActions.navigateToFavouriteScreen,
                closeDrawer = {
                    scope.launch {
                        drawerState.close()
                    }
                })
        },
        drawerState = drawerState
    ) {
        NewsyNavigationGraph(
            navActions =  navActions,
            navController = navController,
            openDrawer = {
                scope.launch {
                    drawerState.open()
                }
            }
        )
    }
}