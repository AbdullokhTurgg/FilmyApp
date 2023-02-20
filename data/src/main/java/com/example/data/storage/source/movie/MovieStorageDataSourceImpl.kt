package com.example.data.storage.source.movie

import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.movie.tv_shows.SeriesData
import com.example.data.storage.movie.movie.MovieDao
import com.example.data.storage.movie.movie.MovieStorage
import com.example.data.storage.tv.models.TvStorage
import com.example.data.storage.tv.room.TvDao
import com.example.domain.base.BaseMapper
import com.example.domain.helper.DispatchersProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MovieStorageDataSourceImpl @Inject constructor(
    private val movieDao: MovieDao,
    private val tvDao: TvDao,
    private val mapFromStorage: BaseMapper<MovieStorage, MovieData>,
    private val mapToStorage: BaseMapper<MovieData, MovieStorage>,
    private val dispatchersProvider: DispatchersProvider,
    private val mapperSeriesDataToStorage: BaseMapper<SeriesData, TvStorage>,
    private val mapperListTvStorageToData: BaseMapper<List<TvStorage>, List<SeriesData>>,
) : MovieStorageDataSource {

    override fun getAllMoviesFromDatabase(): Flow<List<MovieData>> = movieDao.getMoviesList()
        .flowOn(dispatchersProvider.io())
        .map(::mapMovieStorageToData)
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


    override suspend fun saveTv(tv: SeriesData) =
        withContext(Dispatchers.IO) {
            tvDao.saveTv(mapperSeriesDataToStorage.map(tv))
        }

    override suspend fun deleteTV(id: Int) =
        withContext(Dispatchers.IO) {
            tvDao.deleteTVFromSaveStorage(id)
        }

    override fun getTvStorage(): Flow<List<SeriesData>> =
        tvDao.getTvStorage()
            .flowOn(Dispatchers.IO)
            .map(mapperListTvStorageToData::map)
            .flowOn(Dispatchers.Default)
}