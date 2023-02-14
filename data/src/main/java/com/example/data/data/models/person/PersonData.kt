package com.example.data.data.models.person

import com.example.data.data.models.movie.MovieData

data class PersonData(
    val profile_path: String?,
    val adult: Boolean,
    val id: Int,
    val known_for: List<MovieData>,
    val name: String,
    val popularity: Double,
)