package com.example.data.cloud.models.person

import com.example.data.cloud.models.movie.MovieCloud
import com.google.gson.annotations.SerializedName

data class PersonCloud(
    @SerializedName("profile_path") val profile_path: String?,
    @SerializedName("adult") val adult: Boolean,
    @SerializedName("id") val id: Int,
    @SerializedName("known_for") val known_for: List<MovieCloud>,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Double,


//    @SerializedName("cast_id")
//    val castId: Int,
//    @SerializedName("character")
//    val character: String,
//    @SerializedName("credit_id")
//    val creditId: String,
//    @SerializedName("gender")
//    val gender: Int,
//    @SerializedName("known_for_department")
//    val knownForDepartment: String,
//    @SerializedName("order")
//    val order: Int,
//    @SerializedName("original_name")
//    val originalName: String,
//    @SerializedName("profile_path")
//    val profilePath: String?
)