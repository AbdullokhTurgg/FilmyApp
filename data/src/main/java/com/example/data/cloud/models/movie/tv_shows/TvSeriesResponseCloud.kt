package com.example.data.cloud.models.movie.tv_shows

import com.example.data.cloud.models.movie.tv_shows.SeriesCloud
import com.google.gson.annotations.SerializedName

data class TvSeriesResponseCloud(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val results: List<SeriesCloud>,
    @SerializedName("total_pages") val total_pages: Int,
    @SerializedName("total_results") val total_results: Int
)
