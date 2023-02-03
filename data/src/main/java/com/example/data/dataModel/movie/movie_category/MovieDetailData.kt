package com.example.data.dataModel.movie.movie_category

import com.example.domain.domainModels.movie.MovieDomain

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