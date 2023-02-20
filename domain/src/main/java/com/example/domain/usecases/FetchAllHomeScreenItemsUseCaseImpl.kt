package com.example.domain.usecases

import com.example.domain.helper.DispatchersProvider
import com.example.domain.models.HomeScreenItems
import com.example.domain.repositories.network.movie.MovieRepositories
import kotlinx.coroutines.flow.*

class FetchAllHomeScreenItemsUseCaseImpl(
    private val dispatchersProvider: DispatchersProvider,
    movieRepositories: MovieRepositories,
) : FetchAllHomeScreenItemsUseCase {

    override fun invoke(): Flow<HomeScreenItems> = combine(
        popularMoviesFlow,
        upcomingMoviesFlow,
        topRatedMoviesFlow,
        nowPlayingMoviesFlow
    ) { popularMovies, upcomingMovies, topRatedMovies, nowPlayingMovies ->
        HomeScreenItems(
            popularMovies = popularMovies.movies,
            upcomingMovies = upcomingMovies.movies,
            topRatedMovies = topRatedMovies.movies,
            nowPlayingMovies = nowPlayingMovies.movies
        )
    }.flowOn(dispatchersProvider.default())


    private val popularMoviesFlow = movieRepositories.getPopularMovie(1)
        .flowOn(dispatchersProvider.io())

    private val upcomingMoviesFlow = movieRepositories.getUpcomingMovies(1)
        .flowOn(dispatchersProvider.io())

    private val topRatedMoviesFlow = movieRepositories.getTopRatedMovies(1)
        .flowOn(dispatchersProvider.io())

    private val nowPlayingMoviesFlow = movieRepositories.getNowPlayingMovies(1)
        .flowOn(dispatchersProvider.io())

}