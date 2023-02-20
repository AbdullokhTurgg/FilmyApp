package com.example.data.storage.movie.mappers

import com.example.data.data.models.movie.MovieData
import com.example.data.storage.movie.movie.MovieStorage
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapFromMovieStorageToData @Inject constructor(): BaseMapper<MovieStorage, MovieData> {
    override fun map(from: MovieStorage) = from.run {
        MovieData(
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