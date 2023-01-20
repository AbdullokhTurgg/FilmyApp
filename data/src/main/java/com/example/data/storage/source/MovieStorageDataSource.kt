package com.example.data.storage.source

import com.example.data.dataModel.movie.MovieData
import kotlinx.coroutines.flow.Flow

interface MovieStorageDataSource {

    fun getAllMoviesFromDatabase(): Flow<List<MovieData>>

    suspend fun saveMovieToDatabase(movie: MovieData)

    suspend fun deleteMovieFromDatabase(movieId: Int)
}