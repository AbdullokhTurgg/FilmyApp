package com.example.data.network.cloud.cloudModels.movie.movie_category

import com.google.gson.annotations.SerializedName

data class CreditsResponseCloud(
    @SerializedName("cast") val cast: List<CastCloud>,

//    @SerializedName("crew") val crew: List<MovieCloud>,
    @SerializedName("id") val id: Int,

    )
