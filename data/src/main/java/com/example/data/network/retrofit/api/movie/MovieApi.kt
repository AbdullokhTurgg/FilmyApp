package com.example.data.network.retrofit.api.movie

import com.example.data.network.cloud.cloudModels.movie.MovieDetailsCloud
import com.example.data.network.cloud.cloudModels.movie.MoviesCloud
import com.example.data.network.cloud.cloudModels.movie.movie_category.CreditsResponseCloud
import com.example.data.network.cloud.cloudModels.person.MovieCrewCloud
import com.example.data.network.retrofit.utils.Endpoints.Movie.DELETE_RATE
import com.example.data.network.retrofit.utils.Endpoints.Movie.MOVIE_DETAILS
import com.example.data.network.retrofit.utils.Endpoints.Movie.NOW_PLAYING
import com.example.data.network.retrofit.utils.Endpoints.Movie.POPULAR
import com.example.data.network.retrofit.utils.Endpoints.Movie.RATE_MOVIE
import com.example.data.network.retrofit.utils.Endpoints.Movie.RECOMMENDATIONS
import com.example.data.network.retrofit.utils.Endpoints.Movie.SEARCH_MOVIE
import com.example.data.network.retrofit.utils.Endpoints.Movie.SIMILAR
import com.example.data.network.retrofit.utils.Endpoints.Movie.TOP_RATED
import com.example.data.network.retrofit.utils.Endpoints.Movie.UPCOMING
import com.example.data.network.retrofit.utils.Utils
import com.example.data.network.retrofit.utils.Utils.API_KEY
import retrofit2.Response
import retrofit2.http.*

interface MovieApi {
    @GET(POPULAR)
    suspend fun getPopularMovies(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("page") @androidx.annotation.IntRange(from = 1) page: Int = 1,
//        @Query("pageSize") @androidx.annotation.IntRange(from = 1,
//            to = MAX_PAGE_SIZE.toLong()) pageSize: Int = DEFAULT_PAGE_SIZE,
    ): Response<MoviesCloud>

//    @GET("genre/movie/list?api_key=e2c019e3bbc9049df7b03972b44ff529")
//    suspend fun getCategories(): MoviesCloud

    // People
    @GET("3/movie/{movieId}/credits?api_key=$API_KEY")
    suspend fun getMovieCredit(
        @Path("movieId") movieId: String,
        @Query("language") language: String,
    ): Response<MovieCrewCloud>

    @GET(TOP_RATED)
    suspend fun getTopRatedMovies(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int,
    ): Response<MoviesCloud>

    @GET(UPCOMING)
    suspend fun getUpcomingMovies(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int?,
    ): Response<MoviesCloud>

    @GET(NOW_PLAYING)
    suspend fun getNowPlayingMovies(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int?,
    ): Response<MoviesCloud>

    @GET(SEARCH_MOVIE)
    suspend fun searchMovies(
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = "en",
        @Query("query") query: String?,
    ): Response<MoviesCloud>

    @GET(MOVIE_DETAILS)
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = "en",
        @Query("page") page: Int? = 1,
    ): Response<MovieDetailsCloud>

    @GET(SIMILAR)
    suspend fun getSimilarMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = "en",
    ): Response<MoviesCloud>

    @GET(RECOMMENDATIONS)
    suspend fun getRecommendMovies(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = "en",
    ): Response<MoviesCloud>

    @POST(RATE_MOVIE)
    suspend fun postRateForMovie(
        @Path("movie_id") movieId: Int,
        @Header("Content-Type") contentType: String = "application/json",
        @Query("api_key") apiKey: String = Utils.API_KEY,
    ): Response<MoviesCloud>

    @DELETE(DELETE_RATE)
    suspend fun deleteMovieRate(
        @Path("movie_id") movieId: Int,
        @Header("Content-Type") contentType: String = "application/json",
        @Query("api_key") apiKey: String = Utils.API_KEY,
    ): Response<MoviesCloud>


    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(
        @Path("movie_id") movieId: Int,
        @Query("api_key") apiKey: String = API_KEY,
        @Query("language") language: String = "ru",
    ): Response<CreditsResponseCloud>









    // Example
    @GET("/3/movie/popular?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getPopular(): Response<MoviesCloud>

    @GET("/3/movie/top_rated?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getTopRated(): Response<MoviesCloud>

    @GET("/3/movie/upcoming?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getUpcoming(): Response<MoviesCloud>

    @GET("/3/movie/{id}?api_key=e2c019e3bbc9049df7b03972b44ff529&append_to_response=credits")
    suspend fun getMovieDetail(@Path("id") id: Int): MoviesCloud

    @GET("/3/genre/movie/list?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getCategories(): MoviesCloud

    @GET("/3/discover/movie?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getCategoryDetail(@Query("with_genres") id: Int): MoviesCloud

    @GET("/3/search/movie?api_key=e2c019e3bbc9049df7b03972b44ff529")
    suspend fun getSearch(@Query("query") query: String): Response<MoviesCloud>
}

const val DEFAULT_PAGE_SIZE = 1
const val MAX_PAGE_SIZE = 20