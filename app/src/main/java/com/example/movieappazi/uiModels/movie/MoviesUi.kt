package com.example.movieappazi.uiModels.movie

data class MoviesUi(
    val page: Int,
    val movies: List<MovieUi>,
    val totalPage: Int,
)