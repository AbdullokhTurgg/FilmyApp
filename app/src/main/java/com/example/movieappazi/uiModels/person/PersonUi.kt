package com.example.movieappazi.uiModels.person

import com.example.movieappazi.uiModels.movie.MovieUi

data class PersonUi(
    val profile_path: String?,
    val adult: Boolean,
    val id: Int,
    val known_for: List<MovieUi>,
    val name: String,
    val popularity: Double,
)