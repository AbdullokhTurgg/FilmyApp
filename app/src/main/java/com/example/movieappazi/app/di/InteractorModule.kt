package com.example.movieappazi.app.di

import com.example.domain.helper.DispatchersProvider
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.domain.usecases.FetchAllHomeScreenItemsUseCase
import com.example.domain.usecases.FetchAllHomeScreenItemsUseCaseImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object InteractorModule {

    @Provides
    fun provideFetchAllHomeScreenItemUseCase(
        dispatchersProvider: DispatchersProvider,
        movieRepositories: MovieRepositories,
    ): FetchAllHomeScreenItemsUseCase =
        FetchAllHomeScreenItemsUseCaseImpl(dispatchersProvider = dispatchersProvider,
            movieRepositories = movieRepositories)
}