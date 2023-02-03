package com.example.data.dataModel.movie.movie_category

sealed class MovieHomeTypeModelData {
    data class Title(
        val title: String?,
    ) : MovieHomeTypeModelData()

    data class Horizontal(
        val data: List<MovieHomeData>,
    ) : MovieHomeTypeModelData()

    data class Vertical(
        val id: Int,
        val title: String?,
        val image: String?,
        val imdb: String?,
    ) : MovieHomeTypeModelData()
}