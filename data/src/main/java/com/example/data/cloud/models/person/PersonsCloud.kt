package com.example.data.cloud.models.person

import com.google.gson.annotations.SerializedName

data class PersonsCloud(
    @SerializedName("page") val page: Int,
    @SerializedName("results") val persons: List<PersonCloud>,
    @SerializedName("total_results") val total_results: Int,
    @SerializedName("total_pages") val total_pages: Int,
) {
    companion object {
        fun unknown() =
            PersonsCloud(page = 0, persons = emptyList(), total_pages = 0, total_results = 0)
    }
}