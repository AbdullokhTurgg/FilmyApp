package com.example.data.storage.movie.mappers

import com.example.data.data.models.movie.MovieData
import com.example.data.storage.movie.movie.MovieStorage
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapFromMovieDataToStorage @Inject constructor(): BaseMapper<MovieData, MovieStorage> {
    override fun map(from: MovieData) = from.run {
        MovieStorage(
            id = id,
            posterPath = posterPath,
            adult = adult,
            overview = overview,
            releaseDate = releaseDate,
            originalTitle = originalTitle,
            originalLanguage = originalLanguage,
            title = title,
            backdropPath = backdropPath,
            popularity = popularity,
            voteCount = voteCount,
            video = video,
            rating = rating,
            genre_ids = genre_ids?.map { id -> id },
        )
    }
}