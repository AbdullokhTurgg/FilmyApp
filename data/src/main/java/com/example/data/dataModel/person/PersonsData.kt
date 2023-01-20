package com.example.data.dataModel.person


data class PersonsData(
    val page: Int,
    val persons: List<PersonData>,
    val total_results: Int,
    val total_pages: Int,
)