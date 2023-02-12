package com.example.movieappazi.ui.adapters.movie.adapter_for_movie_genres

import com.example.movieappazi.uiModels.movie.movie_category.MovieCategoriesUi

data class MovieCategoriesAdapterUiState(
    val isLoading: Boolean = false,
    val categories: List<MovieCategoriesUi> = emptyList(),
    val error: String = "",
)