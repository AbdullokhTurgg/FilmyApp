package com.example.data.network.cloud.cloudModels.movie

import com.google.gson.annotations.SerializedName

data class MovieGenresCloud(
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
)