package com.example.data.network.cloud.cloudModels.movie

import com.google.gson.annotations.SerializedName

data class MoviesCloud(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val movies: List<MovieCloud>,
    @SerializedName("total_pages") val totalPage: Int,  // 123
)