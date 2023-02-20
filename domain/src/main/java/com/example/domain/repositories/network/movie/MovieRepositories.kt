package com.example.domain.repositories.network.movie

import com.example.domain.models.movie.CreditsResponseDomain
import com.example.domain.models.movie.MovieDetailsDomain
import com.example.domain.models.movie.MoviesDomain
import com.example.domain.models.movie.tv_shows.TvSeriesDetailsDomain
import com.example.domain.models.movie.tv_shows.TvSeriesResponseDomain
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.flow.Flow


interface MovieRepositories {
    fun getPopularMovie(page: Int): Flow<MoviesDomain>
    fun getTopRatedMovies(page: Int): Flow<MoviesDomain>
    fun getUpcomingMovies(page: Int): Flow<MoviesDomain>
    fun getNowPlayingMovies(page: Int): Flow<MoviesDomain>
    fun getSearchMovies(query: String): Flow<MoviesDomain>
    fun getSimilarMovies(movieId: Int): Flow<MoviesDomain>
    fun getRecommendMovies(movieId: Int): Flow<MoviesDomain>
    suspend fun getMovieDetails(movieId: Int): DataRequestState<MovieDetailsDomain>
    suspend fun getActors(movieId: Int): DataRequestState<CreditsResponseDomain>
    /**    Tv Shows And Series   */
//    suspend fun getDetails(movieId: Int): Flow<MovieDetailsDomain>
    fun getTrendingTvSeries(page: Int): Flow<TvSeriesResponseDomain>
    fun getTopRatedTvSeries(page: Int): Flow<TvSeriesResponseDomain>
    fun getOnTheAirTvSeries(page: Int): Flow<TvSeriesResponseDomain>
    fun getPopularTvSeries(page: Int): Flow<TvSeriesResponseDomain>
    fun getAiringTodayTvSeries(page: Int): Flow<TvSeriesResponseDomain>
    suspend fun getTvSeriesDetails(tvId: Int): Flow<TvSeriesDetailsDomain>
    fun getTvRecommendations(tvId: Int): Flow<TvSeriesResponseDomain>
    fun getTvSimilar(tvId: Int): Flow<TvSeriesResponseDomain>
    fun getFantasyMovies(page: Int, genres: String): Flow<TvSeriesResponseDomain>



}