package hoods.com.newsy.features_presentations.home.viewModel

import hoods.com.newsy.features_components.core.data.remote.models.NewsyArticle
import hoods.com.newsy.utils.ArticleCategory

sealed class HomeUIEvents {
    object ViewMoreClicked: HomeUIEvents()
    data class ArticleClicked(val url: String): HomeUIEvents()
    data class CategoryChange(val category: ArticleCategory): HomeUIEvents()
    data class PreferencePanelToggle(val isOpen: Boolean): HomeUIEvents()
    data class OnHeadLineFavouriteChange(val article: NewsyArticle): HomeUIEvents()

}