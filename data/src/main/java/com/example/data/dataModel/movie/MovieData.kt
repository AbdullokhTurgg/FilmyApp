package com.example.data.dataModel.movie


data class MovieData(
    val posterPath: String?,
    val adult: Boolean?,
    val overview: String?,
    val releaseDate: String?,
    val id: Int?,
    val originalTitle: String?,
    val originalLanguage: String?,
    val title: String?,
    val backdropPath: String?,
    val popularity: Double?,
    val voteCount: Int?,
    val video: Boolean?,
    val rating: Double?,
    val genre_ids: List<Int>?,
)