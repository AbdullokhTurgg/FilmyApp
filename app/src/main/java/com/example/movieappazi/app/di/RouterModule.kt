package com.example.movieappazi.app.di

import com.example.movieappazi.ui.movie.all_movies_screen.router.FragmentAllMoviesRouter
import com.example.movieappazi.ui.movie.all_movies_screen.router.FragmentAllMoviesRouterImpl
import com.example.movieappazi.ui.movie.search_movies_screen.router.FragmentSearchMoviesRouter
import com.example.movieappazi.ui.movie.search_movies_screen.router.FragmentSearchMoviesRouterImpl
import com.example.movieappazi.ui.movie.see_all_movies_screen.router.FragmentSeeAllRouter
import com.example.movieappazi.ui.movie.see_all_movies_screen.router.FragmentSeeAllRouterImpl
import com.example.movieappazi.ui.movie.storage_movies_screen.router.FragmentStorageRouter
import com.example.movieappazi.ui.movie.storage_movies_screen.router.FragmentStorageRouterImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RouterModule {

    @Binds
    abstract fun provideFragmentAllMoviesRouter(router: FragmentAllMoviesRouterImpl): FragmentAllMoviesRouter

    @Binds
    abstract fun provideFragmentSearchMoviesRouter(router: FragmentSearchMoviesRouterImpl): FragmentSearchMoviesRouter

    @Binds
    abstract fun provideFragmentSeeAllRouter(router: FragmentSeeAllRouterImpl): FragmentSeeAllRouter

    @Binds
    abstract fun provideFragmentStorageRouter(router: FragmentStorageRouterImpl): FragmentStorageRouter

}