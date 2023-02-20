package com.example.movieappazi.ui.movie.see_all_movies_screen.router

import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.navigation.NavCommand
import com.example.movieappazi.app.utils.navigation.toNavCommand
import com.example.movieappazi.ui.movie.see_all_movies_screen.SeeAllMoviesFragmentDirections
import javax.inject.Inject

class FragmentSeeAllRouterImpl @Inject constructor() : FragmentSeeAllRouter {

    override fun navigateToMovieDetailsFragment(movie: MovieUi): NavCommand =
        SeeAllMoviesFragmentDirections
            .actionSeeAllMoviesFragmentToMovieDetailsFragment(movie)
            .toNavCommand()
}