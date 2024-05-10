package hoods.com.newsy.features_presentations.headline.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.newsy.features_components.core.data.remote.models.NewsyArticle
import hoods.com.newsy.features_components.headline.domain.uses_cases.HeadlineUseCases
import hoods.com.newsy.utils.Utils
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HeadlineViewModel @Inject constructor(
    private val headlineUseCases: HeadlineUseCases
) : ViewModel() {
    var headlineState by mutableStateOf(HeadlineState())
        private set


    init {
        initHeadlineArticleData()
    }

    private fun initHeadlineArticleData() {
        val currentCountryCode = Utils.countryCodeList[0].code
        val currentLanguageCode = Utils.languageCodeList[0].code

        headlineState = headlineState.copy(
            headlineArticles = headlineUseCases
                .fetchHeadlineArticleUseCase(
                    headlineState.selectedHeadlineCategory.category,
                    countryCode = currentCountryCode,
                    languageCode = currentLanguageCode
                ).cachedIn(viewModelScope)
        )
    }

    fun onFavouriteChange(article: NewsyArticle) {
        viewModelScope.launch {
            val isFavourite = article.favourite
            article.copy(
                favourite = !isFavourite
            ).also {
                headlineUseCases.updateHeadlineFavouriteUseCase(
                    it
                )
            }
        }
    }

}