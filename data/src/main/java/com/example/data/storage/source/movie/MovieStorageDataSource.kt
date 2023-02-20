package com.example.data.storage.source.movie

import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.movie.tv_shows.SeriesData
import kotlinx.coroutines.flow.Flow

interface MovieStorageDataSource {

    fun getAllMoviesFromDatabase(): Flow<List<MovieData>>
    suspend fun saveMovieToDatabase(movie: MovieData)
    suspend fun deleteMovieFromDatabase(movieId: Int)
    suspend fun getFavouriteMovie(id: Int): MovieData

    suspend fun saveTv(tv: SeriesData)
    suspend fun deleteTV(id: Int)
    fun getTvStorage(): Flow<List<SeriesData>>
}