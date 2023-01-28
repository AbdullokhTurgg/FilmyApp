package com.example.data.network.cloud.source.movie

import com.example.data.dataModel.movie.CreditsResponseData
import com.example.data.dataModel.movie.MovieDetailsData
import com.example.data.dataModel.movie.MoviesData
import com.example.data.network.cloud.cloudModels.movie.MovieDetailsCloud
import com.example.data.network.cloud.cloudModels.movie.MoviesCloud
import com.example.data.network.cloud.cloudModels.movie.movie_category.CreditsResponseCloud
import com.example.data.network.cloud.handler.ResponseHandler
import com.example.data.network.retrofit.api.movie.MovieApi
import com.example.domain.assistant.DispatchersProvider
import com.example.domain.base.BaseMapper
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map

class CloudDataSourceMovieImpl(
    private val api: MovieApi,
    private val mapFromMoviesCloudToData: BaseMapper<MoviesCloud, MoviesData>,
    private val mapFromDetailsCloudToData: BaseMapper<MovieDetailsCloud, MovieDetailsData>,
    private val mapCreditsResponseCloudToData: BaseMapper<CreditsResponseCloud, CreditsResponseData>,
    private val dispatchersProvider: DispatchersProvider,
    private val responseHandler: ResponseHandler,
) : CloudDataSourceMovie {

    override fun getPopularMovie(page: Int): Flow<MoviesData> = flow {
        emit(api.getPopularMovies(page = page))
    }.flowOn(dispatchersProvider.io()).map {
        it.body()!!
    }.map(mapFromMoviesCloudToData::map).flowOn(dispatchersProvider.default())

    override fun getTopRatedMovies(page: Int): Flow<MoviesData> = flow {
        emit(api.getTopRatedMovies(page = page))
    }.flowOn(dispatchersProvider.io()).map {
        it.body()!!
    }.map(mapFromMoviesCloudToData::map).flowOn(dispatchersProvider.default())

    override fun getUpcomingMovies(page: Int): Flow<MoviesData> = flow {
        emit(api.getUpcomingMovies(page = page))
    }.flowOn(dispatchersProvider.io()).map {
        it.body()!!
    }.map(mapFromMoviesCloudToData::map).flowOn(dispatchersProvider.default())


    override fun getNowPlayingMovies(page: Int): Flow<MoviesData> = flow {
        emit(api.getNowPlayingMovies(page = page))
    }.flowOn(dispatchersProvider.io()).map {
        it.body()!!
    }.map(mapFromMoviesCloudToData::map).flowOn(dispatchersProvider.default())

    override suspend fun searchMovie(query: String?): DataRequestState<MoviesData> =
        responseHandler.safeApiMapperCall(mapFromMoviesCloudToData) {
            api.searchMovies(query = query)
        }

    override fun getSimilarMovies(movieId: Int): Flow<MoviesData> = flow {
        emit(api.getSimilarMovies(movieId = movieId))
    }.flowOn(dispatchersProvider.io()).map {
        it.body()!!
    }.map(mapFromMoviesCloudToData::map).flowOn(dispatchersProvider.default())

    override fun getRecommendMovies(movieId: Int): Flow<MoviesData> = flow {
        emit(api.getRecommendMovies(movieId = movieId))
    }.flowOn(dispatchersProvider.io()).map {
        it.body()!!
    }.map(mapFromMoviesCloudToData::map).flowOn(dispatchersProvider.default())

//    override suspend fun getCategories(): MoviesData =
//        mapFromMoviesCloudToData.map(api.getCategories())


    override suspend fun getMovieDetails(
        movieId: Int,
    ): DataRequestState<MovieDetailsData> =
        responseHandler.safeApiMapperCall(mapFromDetailsCloudToData) {
            api.getMovieDetails(id = movieId)
        }

    override fun addRateForMovie(movieId: Int): Flow<MoviesData> = flow {
        emit(api.postRateForMovie(movieId = movieId))
    }.flowOn(dispatchersProvider.io()).map {
        it.body()!!
    }.map(mapFromMoviesCloudToData::map).flowOn(dispatchersProvider.default())


    override fun deleteRateFromMovie(movieId: Int): Flow<MoviesData> = flow {
        emit(api.deleteMovieRate(movieId = movieId))
    }.flowOn(dispatchersProvider.io()).map {
        it.body()!!
    }.map(mapFromMoviesCloudToData::map).flowOn(dispatchersProvider.default())


    override suspend fun getCategories(): MoviesData =
        mapFromMoviesCloudToData.map(api.getCategories())


    override suspend fun getCategoryDetail(id: Int): MoviesData =
        mapFromMoviesCloudToData.map(api.getCategoryDetail(id = id))

    override suspend fun getActors(movieId: Int): DataRequestState<CreditsResponseData> =
        responseHandler.safeApiMapperCall(mapCreditsResponseCloudToData) {
            api.getMovieCredits(movieId = movieId)
        }

}