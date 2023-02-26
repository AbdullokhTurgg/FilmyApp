package com.example.data.cloud.api.api.movie

import androidx.annotation.IntRange
import com.example.data.cloud.api.utils.Endpoints.Movie.AIRING_TODAY_TV
import com.example.data.cloud.api.utils.Endpoints.Movie.DELETE_RATE
import com.example.data.cloud.api.utils.Endpoints.Movie.MOVIE_DETAILS
import com.example.data.cloud.api.utils.Endpoints.Movie.NOW_PLAYING
import com.example.data.cloud.api.utils.Endpoints.Movie.ON_THE_AIR_TV
import com.example.data.cloud.api.utils.Endpoints.Movie.POPULAR
import com.example.data.cloud.api.utils.Endpoints.Movie.POPULAR_TV
import com.example.data.cloud.api.utils.Endpoints.Movie.RATE_MOVIE
import com.example.data.cloud.api.utils.Endpoints.Movie.RECOMMENDATIONS
import com.example.data.cloud.api.utils.Endpoints.Movie.SEARCH_MOVIE
import com.example.data.cloud.api.utils.Endpoints.Movie.SIMILAR
import com.example.data.cloud.api.utils.Endpoints.Movie.TOP_RATED
import com.example.data.cloud.api.utils.Endpoints.Movie.TOP_RATED_TV
import com.example.data.cloud.api.utils.Endpoints.Movie.TRENDING_TV
import com.example.data.cloud.api.utils.Endpoints.Movie.UPCOMING
import com.example.data.cloud.api.utils.Utils.API_KEY
import com.example.data.cloud.models.movie.CreditsResponseCloud
import com.example.data.cloud.models.movie.MovieDetailsCloud
import com.example.data.cloud.models.movie.MoviesCloud
import com.example.data.cloud.models.movie.tv_shows.TvSeriesDetailsCloud
import com.example.data.cloud.models.movie.tv_shows.TvSeriesResponseCloud
import retrofit2.Response
import retrofit2.http.*


interface MovieApi {

    @GET("discover/movie")
    suspend fun getMovieGenres(
        @Query("with_genres") genres: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") lang: String = "ru",
    ): Response<MoviesCloud>

    @GET(POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") lang: String = "en",
    ): Response<MoviesCloud>

    @GET(TOP_RATED)
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") page: Int,
    ): Response<MoviesCloud>

    @GET(UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") page: Int?,
    ): Response<MoviesCloud>

    @GET(NOW_PLAYING)
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") page: Int?,
    ): Response<MoviesCloud>

    @GET(SEARCH_MOVIE)
    suspend fun searchMovies(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
        @Query("query") query: String?,
    ): Response<MoviesCloud>

    @GET("search/tv")
    suspend fun searchSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") lang: String? = "ru",
        @Query("include_adult") includeAdult: Boolean = false,
        @Query("query") searchQuery: String,
    ): Response<TvSeriesResponseCloud>

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") page: Int? = 1,
    ): Response<MovieDetailsCloud>

    @GET(SIMILAR)
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
    ): Response<MoviesCloud>

    @GET(RECOMMENDATIONS)
    suspend fun getRecommendMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
    ): Response<MoviesCloud>

    @POST(RATE_MOVIE)
    suspend fun postRateForMovie(
        @Path("movie_id") movieId: Int,
        @Header("Content-Type") contentType: String = "application/json",
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<MoviesCloud>

    @DELETE(DELETE_RATE)
    suspend fun deleteMovieRate(
        @Path("movie_id") movieId: Int,
        @Header("Content-Type") contentType: String = "application/json",
        @Query("api_key") apiKey: String = API_KEY,
    ): Response<MoviesCloud>


    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
    ): Response<CreditsResponseCloud>

    @GET("movie/{tv_id}/credits")
    suspend fun getTvCredits(
        @Path("tv_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
    ): Response<CreditsResponseCloud>

    // TV Series

    @GET(TRENDING_TV)
    suspend fun getTrendingTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") @IntRange(from = 1) page: Int,
    ): Response<TvSeriesResponseCloud>

    @GET(TOP_RATED_TV)
    suspend fun getTopRatedTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") @IntRange(from = 1) page: Int,
    ): Response<TvSeriesResponseCloud>


    @GET(ON_THE_AIR_TV)
    suspend fun getOnTheAirTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") @IntRange(from = 1) page: Int,
    ): Response<TvSeriesResponseCloud>


    @GET(POPULAR_TV)
    suspend fun getPopularTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") @IntRange(from = 1) page: Int,
    ): Response<TvSeriesResponseCloud>


    @GET(AIRING_TODAY_TV)
    suspend fun getAiringTodayTvSeries(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
        @Query("page") @IntRange(from = 1) page: Int,
    ): Response<TvSeriesResponseCloud>


    @GET("tv/{tv_id}")
    suspend fun getTvSeriesDetails(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
    ): Response<TvSeriesDetailsCloud>


    @GET("tv/{tv_id}/similar")
    suspend fun getTvSimilar(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
    ): Response<TvSeriesResponseCloud>

    @GET("tv/{tv_id}/recommendations")
    suspend fun getTvRecommendations(
        @Path("tv_id") tvId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
    ): Response<TvSeriesResponseCloud>


    @GET("discover/tv")
    suspend fun getFantasySeries(
        @Query("with_genres") genres: String,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("page") page: Int,
        @Query("language") lang: String = "ru",
    ): Response<TvSeriesResponseCloud>

}
