package hoods.com.newsy.features_components.discover.domain.use_cases

import hoods.com.newsy.features_components.core.data.remote.models.NewsyArticle
import hoods.com.newsy.features_components.discover.domain.repository.DiscoverRepository

class UpdateFavoriteDiscoverArticleUseCase(
    private val repository: DiscoverRepository
) {

    suspend operator fun invoke(article: NewsyArticle) {
        repository.updateFavouriteDiscoverCategory(article)
    }
}