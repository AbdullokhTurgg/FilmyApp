package com.example.data.storage.source.movie

import com.example.data.dataModel.movie.MovieData
import com.example.domain.domainModels.movie.MovieDomain
import kotlinx.coroutines.flow.Flow

interface MovieStorageDataSource {

    fun getAllMoviesFromDatabase(): Flow<List<MovieData>>

    suspend fun saveMovieToDatabase(movie: MovieData)

    suspend fun deleteMovieFromDatabase(movieId: Int)

    suspend fun getFavouriteMovie(id: Int): MovieData
}