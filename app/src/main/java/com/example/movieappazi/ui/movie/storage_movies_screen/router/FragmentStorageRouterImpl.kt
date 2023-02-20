package com.example.movieappazi.ui.movie.storage_movies_screen.router

import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.navigation.NavCommand
import com.example.movieappazi.app.utils.navigation.toNavCommand
import com.example.movieappazi.ui.movie.storage_movies_screen.StorageMoviesFragmentDirections
import javax.inject.Inject

class FragmentStorageRouterImpl @Inject constructor() : FragmentStorageRouter {

    override fun navigateToDetailsFragment(movie: MovieUi): NavCommand =
        StorageMoviesFragmentDirections
            .actionNavStorageToMovieDetailsFragment(movie)
            .toNavCommand()
}