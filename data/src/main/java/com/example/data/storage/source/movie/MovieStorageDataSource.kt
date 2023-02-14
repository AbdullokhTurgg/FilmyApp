package com.example.data.storage.source.movie

import com.example.data.data.models.movie.MovieData
import kotlinx.coroutines.flow.Flow

interface MovieStorageDataSource {

    fun getAllMoviesFromDatabase(): Flow<List<MovieData>>

    suspend fun saveMovieToDatabase(movie: MovieData)

    suspend fun deleteMovieFromDatabase(movieId: Int)

    suspend fun getFavouriteMovie(id: Int): MovieData
}