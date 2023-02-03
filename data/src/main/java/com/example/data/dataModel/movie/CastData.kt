package com.example.data.dataModel.movie

class CastData(
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