package com.example.data.cloud.models.movie

import com.google.gson.annotations.SerializedName

data class MovieGenresCloud(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)