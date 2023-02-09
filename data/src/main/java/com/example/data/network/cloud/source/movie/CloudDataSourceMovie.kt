package com.example.data.network.cloud.source.movie

import com.example.data.dataModel.movie.CreditsResponseData
import com.example.data.dataModel.movie.MovieDetailsData
import com.example.data.dataModel.movie.MoviesData
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.flow.Flow

interface CloudDataSourceMovie {
    fun getPopularMovie(page: Int): Flow<MoviesData>
    fun getTopRatedMovies(page: Int): Flow<MoviesData>
    fun getUpcomingMovies(page: Int): Flow<MoviesData>
    fun getNowPlayingMovies(page: Int): Flow<MoviesData>
    suspend fun searchMovie(query: String?): DataRequestState<MoviesData>
    fun getSimilarMovies(movieId: Int): Flow<MoviesData>
    fun getRecommendMovies(movieId: Int): Flow<MoviesData>
    suspend fun getMovieDetails(movieId: Int): DataRequestState<MovieDetailsData>
    fun addRateForMovie(movieId: Int): Flow<MoviesData>
    fun deleteRateFromMovie(movieId: Int): Flow<MoviesData>
    suspend fun getCategories(): MoviesData
    suspend fun getCategoryDetail(id: Int): MoviesData
    suspend fun getActors(movieId: Int): DataRequestState<CreditsResponseData>


}