package com.example.data.cloud.models.movie.movie_category

import com.google.gson.annotations.SerializedName

data class CreditsResponseCloud(
    @SerializedName("cast") val cast: List<CastCloud>,
    @SerializedName("id") val id: Int,

    )
