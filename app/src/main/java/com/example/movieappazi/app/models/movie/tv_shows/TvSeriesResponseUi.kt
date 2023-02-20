package com.example.movieappazi.app.models.movie.tv_shows

class TvSeriesResponseUi(
    val page: Int,
    val series: List<SeriesUi>,
    val total_pages: Int,
    val total_results: Int
)