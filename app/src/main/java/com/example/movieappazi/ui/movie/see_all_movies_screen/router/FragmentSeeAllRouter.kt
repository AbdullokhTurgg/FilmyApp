package com.example.movieappazi.ui.movie.see_all_movies_screen.router

import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.navigation.NavCommand

interface FragmentSeeAllRouter {

    fun navigateToMovieDetailsFragment(movie: MovieUi): NavCommand
}