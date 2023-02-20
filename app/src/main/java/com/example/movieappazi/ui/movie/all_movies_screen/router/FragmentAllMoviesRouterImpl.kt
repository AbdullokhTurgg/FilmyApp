package com.example.movieappazi.ui.movie.all_movies_screen.router

import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.navigation.NavCommand
import com.example.movieappazi.app.utils.navigation.toNavCommand
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragmentDirections
import com.example.movieappazi.ui.movie.see_all_movies_screen.MovieType
import javax.inject.Inject

class FragmentAllMoviesRouterImpl @Inject constructor() : FragmentAllMoviesRouter {

    override fun navigateToMovieDetailsFragment(movieUi: MovieUi): NavCommand =
        AllMoviesFragmentDirections
            .actionAllMoviesFragmentToMovieDetailsFragment(movieUi)
            .toNavCommand()

    override fun navigateToSeeMoreFragment(type: MovieType): NavCommand =
        AllMoviesFragmentDirections
            .actionNavMoviesToSeeAllMoviesFragment2(type)
            .toNavCommand()

}