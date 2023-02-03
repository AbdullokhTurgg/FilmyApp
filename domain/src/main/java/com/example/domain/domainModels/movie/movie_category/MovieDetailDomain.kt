package com.example.domain.domainModels.movie.movie_category

import com.example.domain.domainModels.movie.MoviesDomain

data class MovieDetailDomain(
    val title: String?,
    val image: String?,
    val banner: String?,
    val description: String?,
    val imdb: String?,
    val genres: List<MoviesDomain>,
    val runtime: String?,
    val cast: List<MoviesDomain>?,
)