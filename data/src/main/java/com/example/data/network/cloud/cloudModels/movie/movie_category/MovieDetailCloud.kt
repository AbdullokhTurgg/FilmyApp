package com.example.data.network.cloud.cloudModels.movie.movie_category

import com.example.data.network.cloud.cloudModels.movie.MoviesCloud

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