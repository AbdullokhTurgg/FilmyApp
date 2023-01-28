package com.example.movieappazi.ui.search_movies_screen

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SearchMoviesFragmentViewModel @Inject constructor(
//    private val getCategoriesUseCase: GetCategoriesUseCase,
) : ViewModel() {

//    private val _uiState = MutableStateFlow(MovieCategoriesAdapterUiState())
//    val uiState: StateFlow<MovieCategoriesAdapterUiState> = _uiState
//
//    init {
//        getCategories()
//    }
//
//    private fun getCategories() {
//        getCategoriesUseCase().onEach { result ->
//            when (result) {
//                is Utils.Resource.Success -> {
//                    _uiState.value =
//                        MovieCategoriesAdapterUiState(categories = (result.data ?: emptyList()) as List<MovieCategoriesUi>)
//                }
//                is Utils.Resource.Error -> {
//                    _uiState.value = MovieCategoriesAdapterUiState(error = result.message
//                        ?: "An unexpected error occured.")
//                }
//                is Utils.Resource.Loading -> {
//                    _uiState.value = MovieCategoriesAdapterUiState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
}