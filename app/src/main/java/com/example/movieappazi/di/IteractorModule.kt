package com.example.movieappazi.di

import com.example.domain.assistant.DispatchersProvider
import com.example.domain.domainRepositories.network.person.PersonRepositories
import com.example.domain.iteractors.movie_iteractors.get_movie_actors_details_usecase.GetMovieActorsDetailsUseCase
import com.example.domain.iteractors.movie_iteractors.get_movie_actors_details_usecase.GetMovieActorsDetailsUseCaseImpl
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
    fun provideGetPersonUseCase(
        repository: PersonRepositories,
        dispatchersProvider: DispatchersProvider,
    ): GetMovieActorsDetailsUseCase = GetMovieActorsDetailsUseCaseImpl(repository = repository,
        dispatchersProvider = dispatchersProvider)
}