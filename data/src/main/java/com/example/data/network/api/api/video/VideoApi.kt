package com.example.data.network.api.api.video

import com.example.data.network.cloud.cloudModels.video.VideosCloud
import com.example.data.network.api.utils.Endpoints
import com.example.data.network.api.utils.Utils
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface VideoApi {
    @GET(Endpoints.TRAILER.MOVIE_TRAILERS)
    suspend fun getTrailers(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = Utils.API_KEY,
        @Query("language") language: String = "ru",
    ): Response<VideosCloud>
}