package com.example.domain.models.person

import com.example.domain.models.movie.MovieDomain

class PersonDomain(
    val profile_path: String?,
    val adult: Boolean,
    val id: Int,
    val known_for: List<MovieDomain>,
    val name: String,
    val popularity: Double,
)