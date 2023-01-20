package com.example.data.network.cloud.cloudModels.video

import com.google.gson.annotations.SerializedName

data class VideosCloud(
    @SerializedName("id") val id: Int,
    @SerializedName("results") val trailerList: List<VideoCloud>,
)