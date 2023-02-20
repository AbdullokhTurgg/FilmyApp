package com.example.movieappazi.ui.movie.all_movies_screen.router

import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.navigation.NavCommand
import com.example.movieappazi.ui.movie.see_all_movies_screen.MovieType

interface FragmentAllMoviesRouter {

    fun navigateToMovieDetailsFragment(movieUi: MovieUi): NavCommand

    fun navigateToSeeMoreFragment(type: MovieType): NavCommand
}