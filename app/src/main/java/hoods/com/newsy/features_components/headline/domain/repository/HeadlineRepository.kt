package hoods.com.newsy.features_components.headline.domain.repository

import androidx.paging.PagingData
import hoods.com.newsy.features_components.core.data.remote.models.NewsyArticle
import kotlinx.coroutines.flow.Flow

interface HeadlineRepository {
    fun fetchHeadlineArticle(category: String, language: String): Flow<PagingData<NewsyArticle>>
}