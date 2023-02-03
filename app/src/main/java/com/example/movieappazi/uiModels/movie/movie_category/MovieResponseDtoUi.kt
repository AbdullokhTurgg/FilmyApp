package com.example.movieappazi.uiModels.movie.movie_category

class MovieResponseDtoUi(
    val page: Int,
    val results: List<MovieResponseDtoUi>,
    val totalPages: Int,
    val totalResults: Int,

    val adult: Boolean,
    val backdropPath: String,
    val id: Int,
    val name: String,
    val genreIds: List<Int>,
    val genres: List<MovieResponseDtoUi>,
    val originalLanguage: String,
    val originalTitle: String,
    val overview: String,
    val popularity: Double,
    val posterPath: String,
    val profilePath: String,
    val releaseDate: String,
    val title: String?,
    val video: Boolean,
    val voteAverage: Double,
    val voteCount: Int,
    val runtime: String,

    val credits: MovieResponseDtoUi?,

    val cast: List<MovieResponseDtoUi>,
)