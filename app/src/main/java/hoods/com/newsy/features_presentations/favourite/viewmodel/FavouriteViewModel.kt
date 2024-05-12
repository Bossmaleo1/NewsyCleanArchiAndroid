package hoods.com.newsy.features_presentations.favourite.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.newsy.features_components.favourite.domain.use_cases.FavouriteUseCases
import javax.inject.Inject

@HiltViewModel
class FavouriteViewModel @Inject constructor(
    private val favouriteUseCases: FavouriteUseCases
): ViewModel() {

    var favouriteState by mutableStateOf(FavouriteState())
        private set

    init {
        load()
    }

    private fun load(){
        favouriteState = favouriteState.copy(
            favouriteArticles = favouriteUseCases
                .getAllFavouriteUseCase()
                .cachedIn(viewModelScope)
        )
    }

}