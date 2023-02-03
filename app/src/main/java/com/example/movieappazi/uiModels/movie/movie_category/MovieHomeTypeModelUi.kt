package com.example.movieappazi.uiModels.movie.movie_category

sealed class MovieHomeTypeModelUi {
    data class Title(
        val title: String?,
    ) : MovieHomeTypeModelUi()

    data class Horizontal(
        val data: List<MovieHomeUi>,
    ) : MovieHomeTypeModelUi()

    data class Vertical(
        val id: Int,
        val title: String?,
        val image: String?,
        val imdb: String?,
    ) : MovieHomeTypeModelUi()
}