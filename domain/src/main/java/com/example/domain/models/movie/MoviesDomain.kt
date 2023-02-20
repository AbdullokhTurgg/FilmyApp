package com.example.domain.models.movie

class MoviesDomain(
    val page: Int,
    val movies: List<MovieDomain>,
    val totalPage: Int,
)