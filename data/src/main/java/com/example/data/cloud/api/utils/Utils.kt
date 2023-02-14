package com.example.data.cloud.api.utils

object Utils {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "3249dba9ba8a81c53f42a124fe89e8e5"
    const val POSTER_PATH_URL = "https://image.tmdb.org/t/p/w342"
    const val YOUTUBE_URL = "https://www.youtube.com/watch?v="
    fun posterUrlMake(uri: String?):String {
        return "https://image.tmdb.org/t/p/w780$uri"

    }

}