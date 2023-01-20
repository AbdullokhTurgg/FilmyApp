package com.example.domain.domainModels.person


data class PersonsDomain(
    val page: Int,
    val persons: List<PersonDomain>,
    val total_results: Int,
    val total_pages: Int,
)