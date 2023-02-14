package com.example.data.cloud.models.movie.movie_category

sealed class MovieHomeTypeModelCloud {
    data class Title(
        val title: String?,
    ) : MovieHomeTypeModelCloud()

    data class Horizontal(
        val data: List<MovieHomeCloud>,
    ) : MovieHomeTypeModelCloud()

    data class Vertical(
        val id: Int,
        val title: String?,
        val image: String?,
        val imdb: String?,
    ) : MovieHomeTypeModelCloud()
}