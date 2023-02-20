package com.example.data.data.models.movie.tv_shows

class TvSeriesResponseData(
    val page: Int,
    val results: List<SeriesData>,
    val total_pages: Int,
    val total_results: Int
)