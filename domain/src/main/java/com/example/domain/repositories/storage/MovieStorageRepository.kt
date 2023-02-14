package com.example.domain.repositories.storage

import com.example.domain.domainModels.movie.MovieDomain
import kotlinx.coroutines.flow.Flow

interface MovieStorageRepository {

    suspend fun saveMovieToDatabase(movie: MovieDomain)

    suspend fun deleteMovieFromDatabase(movieId: Int)

    fun getAllMoviesFromDatabase(): Flow<List<MovieDomain>>

    suspend fun getSavedMovies(id:Int): MovieDomain
}