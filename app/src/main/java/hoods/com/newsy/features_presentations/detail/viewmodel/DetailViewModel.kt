package hoods.com.newsy.features_presentations.detail.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import hoods.com.newsy.features_components.detail.domain.use_cases.DetailUseCases
import hoods.com.newsy.features_presentations.core.navigation.UiScreeen
import hoods.com.newsy.utils.K
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val detailUseCases: DetailUseCases,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val unknownRoute = "unknown"
    val id: Int = savedStateHandle.get<Int>(K.articleId) ?: -1
    private val route: String = savedStateHandle.get<String>(K.screenType) ?: unknownRoute

    var detailState by mutableStateOf(DetailState())
        private set

    /*

    class ComputationViewModel : ViewModel() {
    private val _result = MutableStateFlow<Int>(0) // Initial value
    val result: StateFlow<Int> = _result.asStateFlow()

    fun performComputation(input: Int) {
        viewModelScope.launch(Dispatchers.Default) {  // Using the Default dispatcher for CPU-intensive task
            val computedValue = heavyComputation(input)
            _result.emit(computedValue)  // Emitting from any thread is safe
        }
    }

    private fun heavyComputation(input: Int): Int {
        // Simulate a heavy computation
        return input * input
    }
}

     */

    init {
        load()
    }

    private fun load() {
        viewModelScope.launch {
            when(route) {
                UiScreeen.DiscoverScreen().route -> {
                    detailState = detailState.copy(
                        selectedDetailArticle = detailUseCases.getDetailDiscoverArticleUseCase(
                            id
                        ),
                        error = false
                    )
                }
                UiScreeen.HeadlineScreen().route -> {
                    detailState = detailState.copy(
                        selectedDetailArticle = detailUseCases.getDetailHeadlineArticleUseCase(
                            id
                        ),
                        error = false
                    )
                }
                UiScreeen.SearchScreen().route -> {
                    detailState = detailState.copy(
                        selectedDetailArticle = detailUseCases.getDetailSearchArticleUseCase(
                            id
                        ),
                        error = false
                    )
                }
                unknownRoute -> {
                    detailState = detailState.copy(
                        error = true
                    )
                }
            }
        }
    }

}