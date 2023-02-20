package com.example.data.data.domainRepositoryImpl.storage

import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.movie.tv_shows.SeriesData
import com.example.data.storage.source.movie.MovieStorageDataSource
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.tv_shows.SeriesDomain
import com.example.domain.repositories.storage.MovieStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieStorageRepositoryImpl @Inject constructor(
    private val dataSourceStorage: MovieStorageDataSource,
    private val mapFromMovieDomainToData: BaseMapper<MovieDomain, MovieData>,
    private val mapFromMovieDataToDomain: BaseMapper<MovieData, MovieDomain>,
    private val mapperSeriesDomainToData: BaseMapper<SeriesDomain, SeriesData>,
    private val mapSeriesDataToDomain: BaseMapper<SeriesData, SeriesDomain>,
) : MovieStorageRepository {

    override suspend fun saveMovieToDatabase(movie: MovieDomain) =
        dataSourceStorage.saveMovieToDatabase(movie = mapFromMovieDomainToData.map(movie))

    override suspend fun deleteMovieFromDatabase(movieId: Int) =
        dataSourceStorage.deleteMovieFromDatabase(movieId = movieId)


    override fun getAllMoviesFromDatabase(): Flow<List<MovieDomain>> =
        dataSourceStorage.getAllMoviesFromDatabase().map {
            it.map(mapFromMovieDataToDomain::map)
        }

    override suspend fun getSavedMovies(id: Int): MovieDomain =
        mapFromMovieDataToDomain.map(dataSourceStorage.getFavouriteMovie(id))

    override suspend fun tvSave(tv: SeriesDomain) =
        dataSourceStorage.saveTv(tv = mapperSeriesDomainToData.map(tv))


    override suspend fun tvDelete(tvId: Int) = dataSourceStorage.deleteTV(id = tvId)

    override fun tvGetStorage(): Flow<List<SeriesDomain>> =
        dataSourceStorage.getTvStorage().map { movies ->
            movies.map(mapSeriesDataToDomain::map)
        }

}