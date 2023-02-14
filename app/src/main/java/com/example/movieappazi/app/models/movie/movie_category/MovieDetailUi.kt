package com.example.movieappazi.app.models.movie.movie_category

import com.example.movieappazi.app.models.movie.MovieUi

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