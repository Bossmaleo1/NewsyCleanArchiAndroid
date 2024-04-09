package hoods.com.newsy.features_presentations.home.viewModel

import androidx.paging.PagingData
import hoods.com.newsy.features_components.core.data.remote.models.NewsyArticle
import hoods.com.newsy.utils.ArticleCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow

data class HomeState (
    val headlineArticles: Flow<PagingData<NewsyArticle>> = emptyFlow(),
    val selectedHealineCategory: ArticleCategory = ArticleCategory.BUSINESS,

)