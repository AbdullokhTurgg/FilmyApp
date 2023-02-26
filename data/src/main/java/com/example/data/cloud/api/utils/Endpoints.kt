package com.example.data.cloud.api.utils

object Endpoints {
    object Movie {
        const val POPULAR = "movie/popular"
        const val TOP_RATED = "movie/top_rated"
        const val UPCOMING = "movie/upcoming"
        const val NOW_PLAYING = "movie/now_playing"
        const val SEARCH_MOVIE = "search/movie"
        const val MOVIE_DETAILS = "movie/{movie_id}"
        const val SIMILAR = "movie/{movie_id}/similar"
        const val RECOMMENDATIONS = "movie/{movie_id}/recommendations"
        const val RATE_MOVIE = "movie/{movie_id}/rating"
        const val DELETE_RATE = "movie/{movie_id}/rating"

        const val TRENDING_TV = "trending/tv/day"
        const val TOP_RATED_TV = "tv/top_rated"
        const val ON_THE_AIR_TV = "tv/on_the_air"
        const val POPULAR_TV = "tv/popular"
        const val AIRING_TODAY_TV = "tv/airing_today"
    }

    object TRAILER {
        const val MOVIE_TRAILERS = "movie/{movie_id}/videos"
    }

    object Person {
        const val SEARCH_PEOPLE = "search/person"
        const val PERSON_POPULAR = "person/popular"
        const val PERSON_DETAILS = "person/{person_id}"
    }
}