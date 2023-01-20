package com.example.data.dataModel.movie

data class MovieDetailsData(
    val backdrop_path: String?,
    val budget: Int,
    val genres: List<MovieGenresData>,
    val id: Int,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String?,
    val popularity: Double,
    val posterPath: String?,
    val releaseDate: String,
    val runtime: Int?,
    val status: String,
    val title: String,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
)