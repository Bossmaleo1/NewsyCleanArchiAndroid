package hoods.com.newsy.features_presentations.core.navigation

import hoods.com.newsy.utils.K

sealed class UiScreeen {
    data class HomeScreen(val route: String = "home"): UiScreeen()
    data class HeadlineScreen(val route: String = "headline"): UiScreeen()
    data class DiscoverScreen(val route: String = "discover"): UiScreeen()
    data class DetailScreen(
        val route: String = "home",
        val id:String = K.articleId,
        val screen: String = K.screenType,
        val routeWithArg: String = "$route/{${K.articleId}}&{${K.screenType}}"
    ): UiScreeen()
    data class SearchScreen(val route: String = "search"): UiScreeen()
    data class FavouriteScreen(val route: String = "favourite"): UiScreeen()
    data class SettingsScreen(val route: String = "settings"): UiScreeen()

}