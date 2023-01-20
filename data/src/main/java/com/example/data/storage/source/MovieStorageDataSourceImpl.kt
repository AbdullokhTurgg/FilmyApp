package com.example.data.storage.source

import com.example.data.dataModel.movie.MovieData
import com.example.data.storage.db.MovieDao
import com.example.data.storage.model.MovieStorage
import com.example.domain.assistant.DispatchersProvider
import com.example.domain.base.BaseMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class MovieStorageDataSourceImpl(
    private val movieDao: MovieDao,
    private val mapFromStorage: BaseMapper<MovieStorage, MovieData>,
    private val mapToStorage: BaseMapper<MovieData, MovieStorage>,
    private val dispatchersProvider: DispatchersProvider,
) : MovieStorageDataSource {
    override fun getAllMoviesFromDatabase(): Flow<List<MovieData>> =
        movieDao.getMoviesList().flowOn(dispatchersProvider.io()).map(::mapMovieStorageToData)
            .flowOn(dispatchersProvider.default())

    private fun mapMovieStorageToData(movies: List<MovieStorage>) = movies.map(mapFromStorage::map)

    override suspend fun saveMovieToDatabase(movie: MovieData) =
        withContext(dispatchersProvider.io()) {
            movieDao.saveMovie(movie = mapToStorage.map(movie))
        }

    override suspend fun deleteMovieFromDatabase(movieId: Int) =
        withContext(dispatchersProvider.io()) {
            movieDao.deleteMovie(movieId = movieId)
        }
}