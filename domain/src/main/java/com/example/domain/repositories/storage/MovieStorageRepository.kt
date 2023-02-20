package com.example.domain.repositories.storage

import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.tv_shows.SeriesDomain
import kotlinx.coroutines.flow.Flow

interface MovieStorageRepository {

    suspend fun saveMovieToDatabase(movie: MovieDomain)
    suspend fun deleteMovieFromDatabase(movieId: Int)
    fun getAllMoviesFromDatabase(): Flow<List<MovieDomain>>
    suspend fun getSavedMovies(id:Int): MovieDomain


    suspend fun tvSave(tv: SeriesDomain)
    suspend fun tvDelete(tvId: Int)
    fun tvGetStorage(): Flow<List<SeriesDomain>>
}