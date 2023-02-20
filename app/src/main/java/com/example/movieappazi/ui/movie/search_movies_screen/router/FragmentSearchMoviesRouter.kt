package com.example.movieappazi.ui.movie.search_movies_screen.router

import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.navigation.NavCommand

interface FragmentSearchMoviesRouter {

    fun navigateToMovieDetailsFragment(movie: MovieUi): NavCommand
}