package com.example.data.cloud.models.movie.movie_category

import com.example.data.cloud.models.movie.MoviesCloud

data class MovieDetailCloud(
    val title: String?,
    val image: String?,
    val banner: String?,
    val description: String?,
    val imdb: String?,
    val genres: List<MoviesCloud>,
    val runtime: String?,
    val cast: List<MoviesCloud>?,
)