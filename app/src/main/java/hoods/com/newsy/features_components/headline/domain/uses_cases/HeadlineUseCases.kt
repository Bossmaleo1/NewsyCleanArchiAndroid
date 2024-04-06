package hoods.com.newsy.features_components.headline.domain.uses_cases

data class HeadlineUseCases (
    val fetchHeadlineArticleUseCase: FetchHeadlineArticleUseCase,
    val updateHeadlineFavouriteUseCase: UpdateHeadlineFavouriteUseCase
)