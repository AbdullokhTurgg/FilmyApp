package com.example.data.cloud.models.person

import com.google.gson.annotations.SerializedName

data class PersonDetailsCloud(
    @SerializedName("known_for_department") val known_for_department: String,
    @SerializedName("also_known_as") val also_known_as: List<String>,
    @SerializedName("biography") val biography: String,
    @SerializedName("birthday") val birthday: String?,
    @SerializedName("deathday") val deathDay: String?,
    @SerializedName("gender") val gender: Int,
    @SerializedName("id") val id: Int,
    @SerializedName("name") val name: String,
    @SerializedName("popularity") val popularity: Double,
    @SerializedName("place_of_birth") val place_of_birth: String?,
    @SerializedName("profile_path") val profile_path: String?,
)