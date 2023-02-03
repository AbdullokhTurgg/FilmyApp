package com.example.domain.domainModels.movie.movie_category

sealed class MovieHomeTypeModelDomain {
    data class Title(
        val title: String?,
    ) : MovieHomeTypeModelDomain()

    data class Horizontal(
        val data: List<MovieHomeDomain>,
    ) : MovieHomeTypeModelDomain()

    data class Vertical(
        val id: Int,
        val title: String?,
        val image: String?,
        val imdb: String?,
    ) : MovieHomeTypeModelDomain()
}