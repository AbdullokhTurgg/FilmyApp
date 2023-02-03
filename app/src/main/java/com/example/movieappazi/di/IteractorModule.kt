package com.example.movieappazi.di

import com.example.domain.domainRepositories.storage.MovieStorageRepository
import com.example.domain.iteractors.movie_iteractors.GetFavoriteMovieUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class IteractorModule {

    @Provides
    @Singleton
    fun provideGetFavouriteUseCase(
        repository: MovieStorageRepository,
    ): GetFavoriteMovieUseCase = GetFavoriteMovieUseCase(repository = repository)
}