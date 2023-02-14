package com.example.movieappazi.app.models.person


data class PersonsUi(
    val page: Int,
    val persons: List<PersonUi>,
    val total_results: Int,
    val total_pages: Int,
)