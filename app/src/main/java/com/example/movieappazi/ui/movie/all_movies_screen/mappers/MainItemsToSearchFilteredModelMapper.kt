package com.example.movieappazi.ui.movie.all_movies_screen.mappers

import com.example.domain.models.HomeScreenItems
import com.example.movieappazi.app.recyclerview.Item
import com.example.movieappazi.ui.movie.all_movies_screen.listeners.MovieItemOnClickListener

interface MainItemsToSearchFilteredModelMapper {

    fun map(
        items: HomeScreenItems,
        movieItemOnClickListener: MovieItemOnClickListener,
    ): Triple<List<Item>, List<Item>, List<Item>>

}