package com.example.data.domainRepositoryImpl.storage

import com.example.data.dataModel.movie.MovieData
import com.example.data.storage.source.MovieStorageDataSource
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainRepositories.storage.MovieStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class MovieStorageRepositoryImpl(
    private val dataSourceStorage: MovieStorageDataSource,
    private val mapFromMovieDomainToData: BaseMapper<MovieDomain, MovieData>,
    private val mapFromMovieDataToDomain: BaseMapper<MovieData, MovieDomain>,
) : MovieStorageRepository {

    override suspend fun saveMovieToDatabase(movie: MovieDomain) =
        dataSourceStorage.saveMovieToDatabase(movie = mapFromMovieDomainToData.map(movie))

    override suspend fun deleteMovieFromDatabase(movieId: Int) =
        dataSourceStorage.deleteMovieFromDatabase(movieId = movieId)


    override fun getAllMoviesFromDatabase(): Flow<List<MovieDomain>> =
        dataSourceStorage.getAllMoviesFromDatabase().map { movies ->
            movies.map(mapFromMovieDataToDomain::map)
        }
}