package com.example.domain.models

import com.example.domain.models.movie.MovieDomain

data class HomeScreenItems(
    val popularMovies: List<MovieDomain>,
    val upcomingMovies: List<MovieDomain>,
    val topRatedMovies: List<MovieDomain>,
    val nowPlayingMovies: List<MovieDomain>,
)