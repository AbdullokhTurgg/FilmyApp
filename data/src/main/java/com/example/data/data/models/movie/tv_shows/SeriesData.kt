package com.example.data.data.models.movie.tv_shows

class SeriesData(
    val backdropPath: String?,
    val firstAirDate: String,
    val genreIds: List<Int>,
    val id: Int,
    val name: String,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String?,
    val voteAverage: Double,
    val voteCount: Int
)