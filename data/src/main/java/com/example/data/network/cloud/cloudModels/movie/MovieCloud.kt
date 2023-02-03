package com.example.data.network.cloud.cloudModels.movie

import com.google.gson.annotations.SerializedName


data class MovieCloud(
    @SerializedName("poster_path") val posterPath: String?,
    @SerializedName("adult") val adult: Boolean?,
    @SerializedName("overview") val overview: String?,
    @SerializedName("release_date") val releaseDate: String?,
    @SerializedName("id") val id: Int?,
    @SerializedName("original_title") val originalTitle: String?,
    @SerializedName("original_language") val originalLanguage: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("backdrop_path") val backdropPath: String?,
    @SerializedName("popularity") val popularity: Double?,
    @SerializedName("vote_count") val voteCount: Int?,
    @SerializedName("video") val video: Boolean?,
    @SerializedName("vote_average") val rating: Double?,
    @SerializedName("genre_ids") val genre_ids: List<Int>?,
    val runtime: String,
    val credits: MovieCloud?,
    val cast: List<MovieCloud>,
)