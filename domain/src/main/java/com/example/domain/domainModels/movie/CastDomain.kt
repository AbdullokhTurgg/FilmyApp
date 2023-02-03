package com.example.domain.domainModels.movie

class CastDomain(
    val adult: Boolean,
    val castId: Int,
    val character: String,
    val creditId: String,
    val gender: Int?,
    val id: Int,
    val knownForDepartment: String,
    val name: String,
    val order: Int,
    val originalName: String,
    val popularity: Double,
    val profilePath: String? = "https://pixy.org/src/9/94083.png",
)