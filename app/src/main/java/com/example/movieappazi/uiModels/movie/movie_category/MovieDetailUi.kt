package com.example.movieappazi.uiModels.movie.movie_category

import com.example.movieappazi.uiModels.movie.MovieUi

data class MovieDetailUi(
    val title: String?,
    val image: String?,
    val banner: String?,
    val description: String?,
    val imdb: String?,
    val genres: List<MovieUi>,
    val runtime: String?,
    val cast: List<MovieUi>?,
)