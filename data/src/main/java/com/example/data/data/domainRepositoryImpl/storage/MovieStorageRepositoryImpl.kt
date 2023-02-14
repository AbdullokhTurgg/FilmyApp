package com.example.data.data.domainRepositoryImpl.storage

import com.example.data.data.models.movie.MovieData
import com.example.data.storage.source.movie.MovieStorageDataSource
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.repositories.storage.MovieStorageRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieStorageRepositoryImpl @Inject constructor(
    private val dataSourceStorage: MovieStorageDataSource,
    private val mapFromMovieDomainToData: BaseMapper<MovieDomain, MovieData>,
    private val mapFromMovieDataToDomain: BaseMapper<MovieData, MovieDomain>,
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

}