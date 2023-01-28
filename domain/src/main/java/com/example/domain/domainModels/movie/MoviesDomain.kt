package com.example.domain.domainModels.movie

import com.example.domain.domainModels.movie.movie_category.MovieCategoryDetailsDomain

class MoviesDomain(
    val page: Int,
    val movies: List<MovieDomain>,
    val totalPage: Int,
)





















fun MovieDomain.toCategoryDetail(): MovieCategoryDetailsDomain {
    return MovieCategoryDetailsDomain(
        id = id,
        imdb = voteCount.toString(),
        title = title,
        image = backdropPath,
        poster = posterPath,
    )
}