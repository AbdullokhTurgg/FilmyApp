package com.example.movieappazi.uiModels.movie

import android.os.Parcelable
import com.example.domain.domainModels.movie.MovieDomain
import com.example.movieappazi.adapter.Item
import kotlinx.android.parcel.Parcelize


@Parcelize
class MovieUi(
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