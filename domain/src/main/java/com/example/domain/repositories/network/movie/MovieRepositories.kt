package com.example.domain.repositories.network.movie

import com.example.domain.domainModels.movie.CreditsResponseDomain
import com.example.domain.domainModels.movie.MovieDetailsDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.flow.Flow


interface MovieRepositories {
    fun getPopularMovie(page: Int): Flow<MoviesDomain>
    fun getTopRatedMovies(page: Int): Flow<MoviesDomain>
    fun getUpcomingMovies(page: Int): Flow<MoviesDomain>
    fun getNowPlayingMovies(page: Int): Flow<MoviesDomain>
    suspend fun searchMovie(query: String?): DataRequestState<MoviesDomain>
    fun getSimilarMovies(movieId: Int): Flow<MoviesDomain>
    fun getRecommendMovies(movieId: Int): Flow<MoviesDomain>
    suspend fun getMovieDetails(movieId: Int): DataRequestState<MovieDetailsDomain>
    suspend fun getActors(movieId: Int): DataRequestState<CreditsResponseDomain>


}