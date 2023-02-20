package com.example.movieappazi.ui.movie.storage_movies_screen.router

import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.navigation.NavCommand

interface FragmentStorageRouter {

    fun navigateToDetailsFragment(movieUi: MovieUi): NavCommand
}