package com.example.data.cloud.source.movie

import com.example.data.data.models.movie.CreditsResponseData
import com.example.data.data.models.movie.MovieDetailsData
import com.example.data.data.models.movie.MoviesData
import com.example.data.data.models.movie.tv_shows.TvSeriesDetailsData
import com.example.data.data.models.movie.tv_shows.TvSeriesResponseData
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.flow.Flow

interface CloudDataSourceMovie {
    fun getPopularMovie(page: Int): Flow<MoviesData>
    fun getTopRatedMovies(page: Int): Flow<MoviesData>
    fun getUpcomingMovies(page: Int): Flow<MoviesData>
    fun getNowPlayingMovies(page: Int): Flow<MoviesData>
    fun getAllSearchMovies(query: String): Flow<MoviesData>
    fun getSimilarMovies(movieId: Int): Flow<MoviesData>
    fun getRecommendMovies(movieId: Int): Flow<MoviesData>
    suspend fun getMovieDetails(movieId: Int): DataRequestState<MovieDetailsData>
    fun addRateForMovie(movieId: Int): Flow<MoviesData>
    fun deleteRateFromMovie(movieId: Int): Flow<MoviesData>
    suspend fun getActors(movieId: Int): DataRequestState<CreditsResponseData>
    /**    Tv Shows And Series   */
    fun getAllTrendingTvSeries(page: Int): Flow<TvSeriesResponseData>
    fun getAllTopRatedTvSeries(page: Int): Flow<TvSeriesResponseData>
    fun getAllOnTheAirTvSeries(page: Int): Flow<TvSeriesResponseData>
    fun getAllPopularTvSeries(page: Int): Flow<TvSeriesResponseData>
    fun getAllAiringTodayTvSeries(page: Int): Flow<TvSeriesResponseData>
    suspend fun getAllTvSeriesDetails(tvId: Int): Flow<TvSeriesDetailsData>
    fun getAllTvSimilar(tvId: Int): Flow<TvSeriesResponseData>
    fun getAllTvRecommendations(tvId: Int): Flow<TvSeriesResponseData>

    /**    Tv Shows Genres   */
    fun getAllFantasySeries(page: Int, genres: String): Flow<TvSeriesResponseData>

}