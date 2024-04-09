package hoods.com.newsy.features_presentations.home.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.newsy.features_components.headline.domain.uses_cases.HeadlineUseCases
import hoods.com.newsy.utils.Utils
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val headlineUseCases: HeadlineUseCases
): ViewModel() {

    var homeState by mutableStateOf(HomeState())
        private set

    init {
        loadArticles()
    }

    private fun loadArticles() {
        homeState = homeState.copy(
            headlineArticles =
            headlineUseCases.fetchHeadlineArticleUseCase(
                category = homeState.selectedHealineCategory.category,
                countryCode = Utils.countryCodeList[0].code,
                languageCode = Utils.languageCodeList[0].code
            ).cachedIn(viewModelScope)
        )
    }

    fun onHomeUIEvents(homeUIEvents: HomeUIEvents) {
        when (homeUIEvents) {
            HomeUIEvents.ViewMoreClicked -> {}
            is HomeUIEvents.ArticleClicked -> {}
            is HomeUIEvents.CategoryChange -> {}
            is HomeUIEvents.PreferencePanelToggle -> {}
            is HomeUIEvents.OnHeadLineFavouriteChange -> {
                viewModelScope.launch {
                    val isFavourite = homeUIEvents.article.favourite
                    val update = homeUIEvents.article.copy(
                        favourite = !isFavourite
                    )
                    headlineUseCases.updateHeadlineFavouriteUseCase(
                        update
                    )
                }
            }
        }
    }
}