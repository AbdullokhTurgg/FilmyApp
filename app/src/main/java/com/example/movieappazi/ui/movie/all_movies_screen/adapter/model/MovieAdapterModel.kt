package com.example.movieappazi.ui.movie.all_movies_screen.adapter.model

import com.example.movieappazi.app.recyclerview.Item
import com.example.movieappazi.ui.movie.all_movies_screen.listeners.MovieItemOnClickListener

data class MovieAdapterModel(
    val id:String,
    val posterImg:String,
    val listener: MovieItemOnClickListener,
) : Item