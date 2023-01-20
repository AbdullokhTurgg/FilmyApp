package com.example.movieappazi.uiModels.person

data class PersonDetailsUi(
    val known_for_department: String,
    val also_known_as: List<String>,
    val biography: String,
    val birthday: String?,
    val deathDay: String?,
    val gender: Int,
    val id: Int,
    val name: String,
    val popularity: Double,
    val place_of_birth: String?,
    val profile_path: String?,
)