package hoods.com.newsy.features_components.headline.domain.uses_cases

import androidx.paging.PagingData
import hoods.com.newsy.features_components.core.domain.models.NewsyArticle
import hoods.com.newsy.features_components.headline.domain.repository.HeadlineRepository
import kotlinx.coroutines.flow.Flow

class FetchHeadlineArticleUseCase(
    private val repository: HeadlineRepository
) {
    operator fun invoke(
        category: String,
        countryCode: String,
        languageCode: String
    ): Flow<PagingData<NewsyArticle>> = repository.fetchHeadlineArticle(
        category,countryCode,languageCode
    )
}