package com.example.movieappazi.ui.movie.all_movies_screen.adapter.base

import android.os.Parcelable
import com.example.movieappazi.app.recyclerview.Item

data class AllMoviesPopularHorizontalItem(
    val items: List<Item>,
    var state: Parcelable? = null,
) : Item