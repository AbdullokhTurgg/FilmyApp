package com.example.data.cloud.source.movie

import com.example.data.cloud.api.api.movie.MovieApi
import com.example.data.cloud.models.movie.MovieDetailsCloud
import com.example.data.cloud.models.movie.MoviesCloud
import com.example.data.cloud.models.movie.CreditsResponseCloud
import com.example.data.cloud.models.movie.tv_shows.TvSeriesDetailsCloud
import com.example.data.cloud.models.movie.tv_shows.TvSeriesResponseCloud
import com.example.data.cloud.source.handler.ResponseHandler
import com.example.data.data.models.movie.CreditsResponseData
import com.example.data.data.models.movie.MovieDetailsData
import com.example.data.data.models.movie.MoviesData
import com.example.data.data.models.movie.tv_shows.TvSeriesDetailsData
import com.example.data.data.models.movie.tv_shows.TvSeriesResponseData
import com.example.domain.base.BaseMapper
import com.example.domain.helper.DispatchersProvider
import com.example.domain.state.DataRequestState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CloudDataSourceMovieImpl @Inject constructor(
    private val api: MovieApi,
    private val mapFromMoviesCloudToData: BaseMapper<MoviesCloud, MoviesData>,
    private val mapFromDetailsCloudToData: BaseMapper<MovieDetailsCloud, MovieDetailsData>,
    private val mapCreditsResponseCloudToData: BaseMapper<CreditsResponseCloud, CreditsResponseData>,
    private val dispatchersProvider: DispatchersProvider,
    private val mapTvResponseCloudToData: BaseMapper<TvSeriesResponseCloud, TvSeriesResponseData>,
    private val mapTvDetailsCloudToData: BaseMapper<TvSeriesDetailsCloud, TvSeriesDetailsData>,
    private val responseHandler: ResponseHandler,
) : CloudDataSourceMovie {


    override fun getPopularMovie(page: Int): Flow<MoviesData> =
        flow { emit(api.getPopularMovies(page = page)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapFromMoviesCloudToData::map)
            .flowOn(dispatchersProvider.default())

    override fun getTopRatedMovies(page: Int): Flow<MoviesData> =
        flow { emit(api.getTopRatedMovies(page = page)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapFromMoviesCloudToData::map)
            .flowOn(dispatchersProvider.default())

    override fun getUpcomingMovies(page: Int): Flow<MoviesData> =
        flow { emit(api.getUpcomingMovies(page = page)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapFromMoviesCloudToData::map)
            .flowOn(dispatchersProvider.default())


    override fun getNowPlayingMovies(page: Int): Flow<MoviesData> =
        flow { emit(api.getNowPlayingMovies(page = page)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapFromMoviesCloudToData::map)
            .flowOn(dispatchersProvider.default())

    override fun getAllSearchMovies(query: String): Flow<MoviesData> =
        flow { emit(api.searchMovies(query = query)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapFromMoviesCloudToData::map)
            .flowOn(dispatchersProvider.default())

    override fun getAllSearchSeries(query: String): Flow<TvSeriesResponseData> =
        flow { emit(api.searchSeries(searchQuery = query)) }
            .flowOn(dispatchersProvider.io())
            .map{it.body()!!}
            .map(mapTvResponseCloudToData::map)
            .flowOn(dispatchersProvider.default())

    override fun getSimilarMovies(movieId: Int): Flow<MoviesData> =
        flow { emit(api.getSimilarMovies(movieId = movieId)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapFromMoviesCloudToData::map)
            .flowOn(dispatchersProvider.default())

    override fun getRecommendMovies(movieId: Int): Flow<MoviesData> =
        flow { emit(api.getRecommendMovies(movieId = movieId)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapFromMoviesCloudToData::map)
            .flowOn(dispatchersProvider.default())

    override fun getMovieDetails(movieId: Int): Flow<MovieDetailsData> =
        flow { emit(api.getMovieDetails(movieId)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapFromDetailsCloudToData::map)
            .flowOn(dispatchersProvider.default())

    override fun addRateForMovie(movieId: Int): Flow<MoviesData> =
        flow { emit(api.postRateForMovie(movieId = movieId)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapFromMoviesCloudToData::map)
            .flowOn(dispatchersProvider.default())


    override fun deleteRateFromMovie(movieId: Int): Flow<MoviesData> =
        flow { emit(api.deleteMovieRate(movieId = movieId)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapFromMoviesCloudToData::map)
            .flowOn(dispatchersProvider.default())

    override fun getActors(movieId: Int): Flow<CreditsResponseData> =
        flow { emit(api.getMovieCredits(movieId = movieId)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapCreditsResponseCloudToData::map)
            .flowOn(dispatchersProvider.default())

    override fun getTvCasts(tvId: Int): Flow<CreditsResponseData> =
        flow { emit(api.getTvCredits(tvId)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapCreditsResponseCloudToData::map)
            .flowOn(dispatchersProvider.default())

    /**    Tv Shows And Series   */

    override fun getAllTrendingTvSeries(page: Int): Flow<TvSeriesResponseData> =
        flow { emit(api.getTrendingTvSeries(page = page)) }
            .flowOn(Dispatchers.IO)
            .map { it.body()!! }
            .map(mapTvResponseCloudToData::map)
            .flowOn(Dispatchers.Default)

    override fun getAllTopRatedTvSeries(page: Int): Flow<TvSeriesResponseData> =
        flow { emit(api.getTopRatedTvSeries(page = page)) }
            .flowOn(Dispatchers.IO)
            .map { it.body()!! }
            .map(mapTvResponseCloudToData::map)
            .flowOn(Dispatchers.Default)

    override fun getAllOnTheAirTvSeries(page: Int): Flow<TvSeriesResponseData> =
        flow { emit(api.getOnTheAirTvSeries(page = page)) }
            .flowOn(Dispatchers.IO)
            .map { it.body()!! }
            .map(mapTvResponseCloudToData::map)
            .flowOn(Dispatchers.Default)

    override fun getAllPopularTvSeries(page: Int): Flow<TvSeriesResponseData> =
        flow { emit(api.getPopularTvSeries(page = page)) }
            .flowOn(Dispatchers.IO)
            .map { it.body()!! }
            .map(mapTvResponseCloudToData::map)
            .flowOn(Dispatchers.Default)

    override fun getAllAiringTodayTvSeries(page: Int): Flow<TvSeriesResponseData> =
        flow { emit(api.getAiringTodayTvSeries(page = page)) }
            .flowOn(Dispatchers.IO)
            .map { it.body()!! }
            .map(mapTvResponseCloudToData::map)
            .flowOn(Dispatchers.Default)

    override suspend fun getAllTvSeriesDetails(tvId: Int): Flow<TvSeriesDetailsData> =
        flow { emit(api.getTvSeriesDetails(tvId = tvId)) }
            .flowOn(Dispatchers.IO)
            .map { it.body()!! }
            .map(mapTvDetailsCloudToData::map)
            .flowOn(Dispatchers.Default)

    override fun getAllTvSimilar(tvId: Int): Flow<TvSeriesResponseData> =
        flow { emit(api.getTvSimilar(tvId = tvId)) }
            .flowOn(Dispatchers.IO)
            .map { it.body()!! }
            .map(mapTvResponseCloudToData::map)
            .flowOn(Dispatchers.Default)

    override fun getAllTvRecommendations(tvId: Int): Flow<TvSeriesResponseData> =
        flow { emit(api.getTvRecommendations(tvId = tvId)) }
            .flowOn(Dispatchers.IO)
            .map { it.body()!! }
            .map(mapTvResponseCloudToData::map)
            .flowOn(Dispatchers.Default)

    override fun getAllFantasySeries(page: Int, genres: String): Flow<TvSeriesResponseData> =
        flow { emit(api.getFantasySeries(page = page, genres = genres)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapTvResponseCloudToData::map)
            .flowOn(dispatchersProvider.default())

    override fun getAllFantasyMovies(page: Int, genres: String): Flow<MoviesData> =
        flow { emit(api.getMovieGenres(page = page, genres = genres)) }
            .flowOn(dispatchersProvider.io())
            .map { it.body()!! }
            .map(mapFromMoviesCloudToData::map)
            .flowOn(dispatchersProvider.default())




}