package com.example.data.storage.source.movie

import com.example.data.data.models.movie.MovieData
import com.example.data.storage.db.movie.MovieDao
import com.example.data.storage.model.movie.MovieStorage
import com.example.domain.base.BaseMapper
import com.example.domain.helper.DispatchersProvider
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieStorageDataSourceImpl @Inject constructor(
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

    override suspend fun getFavouriteMovie(id: Int): MovieData =
        mapFromStorage.map(movieDao.getMovie(id))
}