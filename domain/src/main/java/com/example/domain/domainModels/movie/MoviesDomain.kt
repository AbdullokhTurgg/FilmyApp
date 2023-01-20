package com.example.domain.domainModels.movie

class MoviesDomain(
    val page: Int,
    val movies: List<MovieDomain>,
    val totalPage: Int,
)