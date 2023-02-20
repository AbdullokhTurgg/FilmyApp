package com.example.data.data.models.movie.movie_category

import com.example.domain.models.movie.MovieDomain

data class MovieDetailData(
    val title: String?,
    val image: String?,
    val banner: String?,
    val description: String?,
    val imdb: String?,
    val genres: List<MovieDomain>,
    val runtime: String?,
    val cast: List<MovieDomain>?,
)