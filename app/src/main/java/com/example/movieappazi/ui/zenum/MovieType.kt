package com.example.movieappazi.ui.zenum

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class MovieType : Parcelable {
    POPULAR, TOP_RATED, NOW_PLAYING, UPCOMING,
}