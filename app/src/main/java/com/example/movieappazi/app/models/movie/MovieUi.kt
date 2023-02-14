package com.example.movieappazi.app.models.movie

import android.os.Parcelable
import com.example.movieappazi.app.recyclerview.Item
import kotlinx.android.parcel.Parcelize


@Parcelize
data class MovieUi(
    val posterPath: String?,
    val adult: Boolean?,
    val overview: String?,
    val releaseDate: String?,
    val id: Int?,
    val originalTitle: String?,
    val originalLanguage: String?,
    val title: String,
    val backdropPath: String?,
    val popularity: Double?,
    val voteCount: Int?,
    val video: Boolean?,
    val rating: Double,
    val genre_ids: List<Int>?,
//    val runtime: String,
//    val credits: MovieUi?,
//    val cast: List<MovieUi>,
) : Parcelable, Item