package com.example.movieappazi.ui.movie.search_movies_screen.router

import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.navigation.NavCommand
import com.example.movieappazi.app.utils.navigation.toNavCommand
import com.example.movieappazi.ui.movie.search_movies_screen.SearchMoviesFragmentDirections
import javax.inject.Inject

class FragmentSearchMoviesRouterImpl @Inject constructor() : FragmentSearchMoviesRouter {

    override fun navigateToMovieDetailsFragment(movie: MovieUi): NavCommand =
        SearchMoviesFragmentDirections
            .actionNavSearchToMovieDetailsFragment(movie)
            .toNavCommand()
}