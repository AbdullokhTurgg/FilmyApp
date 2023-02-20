package com.example.data.data.models.movie.tv_shows

class TvSeriesDetailsData(
    val adult: Boolean,
    val backdropPath: String,
    val episodeRunTime: List<Int>,
    val firstAirDate: String,
    val homepage: String,
    val id: Int,
    val inProduction: Boolean,
    val languages: List<String>,
    val lastAirDate: String,
    val name: String,
    val numberOfEpisodes: Int,
    val numberOfSeasons: Int,
    val originCountry: List<String>,
    val originalLanguage: String,
    val originalName: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val status: String,
    val tagline: String,
    val type: String,
    val voteAverage: Double,
    val voteCount: Int
)