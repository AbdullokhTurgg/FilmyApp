package com.example.data.data.domainRepositoryImpl.network.movie

import com.example.data.cloud.source.movie.CloudDataSourceMovie
import com.example.data.data.models.movie.CreditsResponseData
import com.example.data.data.models.movie.MovieDetailsData
import com.example.data.data.models.movie.MoviesData
import com.example.data.data.models.movie.tv_shows.TvSeriesDetailsData
import com.example.data.data.models.movie.tv_shows.TvSeriesResponseData
import com.example.domain.base.BaseMapper
import com.example.domain.helper.DispatchersProvider
import com.example.domain.models.movie.CreditsResponseDomain
import com.example.domain.models.movie.MovieDetailsDomain
import com.example.domain.models.movie.MoviesDomain
import com.example.domain.models.movie.tv_shows.TvSeriesDetailsDomain
import com.example.domain.models.movie.tv_shows.TvSeriesResponseDomain
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
    private val mapTvResponseDataToDomain: BaseMapper<TvSeriesResponseData, TvSeriesResponseDomain>,
    private val mapTvDetailsDataToDomain: BaseMapper<TvSeriesDetailsData, TvSeriesDetailsDomain>,
    private val dispatchersProvider: DispatchersProvider,
) : MovieRepositories {


    override fun getPopularMovie(page: Int): Flow<MoviesDomain> =
        cloudDataSourceMovie.getPopularMovie(page = page)
            .map(mapFromMoviesDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getTopRatedMovies(page: Int): Flow<MoviesDomain> =
        cloudDataSourceMovie.getTopRatedMovies(page = page)
            .map(mapFromMoviesDataToDomain::map)
            .flowOn(dispatchersProvider.default())


    override fun getUpcomingMovies(page: Int): Flow<MoviesDomain> =
        cloudDataSourceMovie.getUpcomingMovies(page = page)
            .map(mapFromMoviesDataToDomain::map)
            .flowOn(dispatchersProvider.default())


    override fun getNowPlayingMovies(page: Int): Flow<MoviesDomain> =
        cloudDataSourceMovie.getNowPlayingMovies(page = page)
            .map(mapFromMoviesDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getSimilarMovies(movieId: Int): Flow<MoviesDomain> =
        cloudDataSourceMovie.getSimilarMovies(movieId = movieId)
            .map(mapFromMoviesDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getRecommendMovies(movieId: Int): Flow<MoviesDomain> =
        cloudDataSourceMovie.getRecommendMovies(movieId = movieId)
            .map(mapFromMoviesDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getMovieDetails(movieId: Int): Flow<MovieDetailsDomain> =
        cloudDataSourceMovie.getMovieDetails(movieId = movieId)
            .map(mapFromDetailsCloudToData::map)
            .flowOn(dispatchersProvider.default())

    override  fun getActors(movieId: Int): Flow<CreditsResponseDomain> =
        cloudDataSourceMovie.getActors(movieId = movieId)
            .map(mapCreditsResponseData::map)
            .flowOn(dispatchersProvider.default())

    override fun getTvCasts(tvId: Int): Flow<CreditsResponseDomain> =
        cloudDataSourceMovie.getTvCasts(tvId = tvId)
            .map(mapCreditsResponseData::map)
            .flowOn(dispatchersProvider.default())

    /**    Tv Shows And Series   */

    override fun getTrendingTvSeries(page: Int): Flow<TvSeriesResponseDomain> =
        cloudDataSourceMovie.getAllTrendingTvSeries(page = page)
            .map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getTopRatedTvSeries(page: Int): Flow<TvSeriesResponseDomain> =
        cloudDataSourceMovie.getAllTopRatedTvSeries(page = page)
            .map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getOnTheAirTvSeries(page: Int): Flow<TvSeriesResponseDomain> =
        cloudDataSourceMovie.getAllOnTheAirTvSeries(page = page).map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getPopularTvSeries(page: Int): Flow<TvSeriesResponseDomain> =
        cloudDataSourceMovie.getAllPopularTvSeries(page = page)
            .map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getAiringTodayTvSeries(page: Int): Flow<TvSeriesResponseDomain> =
        cloudDataSourceMovie.getAllAiringTodayTvSeries(page = page)
            .map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override suspend fun getTvSeriesDetails(tvId: Int): Flow<TvSeriesDetailsDomain> =
        cloudDataSourceMovie.getAllTvSeriesDetails(tvId = tvId)
            .map(mapTvDetailsDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getTvRecommendations(tvId: Int): Flow<TvSeriesResponseDomain> =
        cloudDataSourceMovie.getAllTvRecommendations(tvId = tvId)
            .map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getTvSimilar(tvId: Int): Flow<TvSeriesResponseDomain> =
        cloudDataSourceMovie.getAllTvSimilar(tvId = tvId)
            .map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getFantasySeries(page: Int, genres: String): Flow<TvSeriesResponseDomain> =
        cloudDataSourceMovie.getAllFantasySeries(page = page, genres = genres)
            .map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getFantasyMovies(page: Int, genres: String): Flow<MoviesDomain> =
        cloudDataSourceMovie.getAllFantasyMovies(page = page, genres = genres)
            .map(mapFromMoviesDataToDomain::map)
            .flowOn(dispatchersProvider.default())


    override fun getSearchMovies(query: String): Flow<MoviesDomain> =
        cloudDataSourceMovie.getAllSearchMovies(query = query)
            .map(mapFromMoviesDataToDomain::map)
            .flowOn(dispatchersProvider.default())

    override fun getAllSearchSeries(query: String): Flow<TvSeriesResponseDomain> =
        cloudDataSourceMovie.getAllSearchSeries(query = query)
            .map(mapTvResponseDataToDomain::map)
            .flowOn(dispatchersProvider.default())


}