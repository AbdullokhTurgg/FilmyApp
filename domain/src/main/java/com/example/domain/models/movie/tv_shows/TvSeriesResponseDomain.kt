package com.example.domain.models.movie.tv_shows

class TvSeriesResponseDomain(
    val page: Int,
    val results: List<SeriesDomain>,
    val total_pages: Int,
    val total_results: Int
)