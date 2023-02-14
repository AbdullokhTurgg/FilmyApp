package com.example.data.data.domainRepositoryImpl.network.movie

import com.example.data.cloud.source.movie.CloudDataSourceMovie
import com.example.data.data.models.movie.CreditsResponseData
import com.example.data.data.models.movie.MovieDetailsData
import com.example.data.data.models.movie.MoviesData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.CreditsResponseDomain
import com.example.domain.domainModels.movie.MovieDetailsDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.domain.helper.DispatchersProvider
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class MovieRepositoriesImpl @Inject constructor(
    private val cloudDataSourceMovie: CloudDataSourceMovie,
    private val mapFromMoviesDataToDomain: BaseMapper<MoviesData, MoviesDomain>,
    private val mapFromDetailsCloudToData: BaseMapper<MovieDetailsData, MovieDetailsDomain>,
    private val mapCreditsResponseData: BaseMapper<CreditsResponseData, CreditsResponseDomain>,
    private val dispatchersProvider: DispatchersProvider,
) : MovieRepositories {



    override fun getPopularMovie(page: Int): Flow<MoviesDomain> =
        cloudDataSourceMovie.getPopularMovie(page = page).map(mapFromMoviesDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getTopRatedMovies(page: Int): Flow<MoviesDomain> =
        cloudDataSourceMovie.getTopRatedMovies(page = page).map(mapFromMoviesDataToDomain::map)
            .flowOn(dispatchersProvider.default())


    override fun getUpcomingMovies(page: Int): Flow<MoviesDomain> =
        cloudDataSourceMovie.getUpcomingMovies(page = page).map(mapFromMoviesDataToDomain::map)
            .flowOn(dispatchersProvider.default())


    override fun getNowPlayingMovies(page: Int): Flow<MoviesDomain> =
        cloudDataSourceMovie.getNowPlayingMovies(page = page).map(mapFromMoviesDataToDomain::map)
            .flowOn(dispatchersProvider.default())


    override suspend fun searchMovie(query: String?): DataRequestState<MoviesDomain> =
        cloudDataSourceMovie.searchMovie(query = query).map(mapFromMoviesDataToDomain)


    override fun getSimilarMovies(movieId: Int): Flow<MoviesDomain> =
        cloudDataSourceMovie.getSimilarMovies(movieId = movieId).map(mapFromMoviesDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getRecommendMovies(movieId: Int): Flow<MoviesDomain> =
        cloudDataSourceMovie.getRecommendMovies(movieId = movieId)
            .map(mapFromMoviesDataToDomain::map).flowOn(dispatchersProvider.default())


    override suspend fun getMovieDetails(movieId: Int): DataRequestState<MovieDetailsDomain> =
        cloudDataSourceMovie.getMovieDetails(movieId = movieId).map(mapFromDetailsCloudToData)


    override suspend fun getActors(movieId: Int): DataRequestState<CreditsResponseDomain> =
        cloudDataSourceMovie.getActors(movieId = movieId).map(mapCreditsResponseData)


}