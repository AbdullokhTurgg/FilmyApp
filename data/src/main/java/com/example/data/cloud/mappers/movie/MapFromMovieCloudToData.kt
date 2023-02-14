package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.MovieCloud
import com.example.data.data.models.movie.MovieData
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapFromMovieCloudToData @Inject constructor(
) : BaseMapper<MovieCloud, MovieData> {
    override fun map(from: MovieCloud) = from.run {
        MovieData(
            posterPath = posterPath,
            adult = adult,
            overview = overview,
            releaseDate = releaseDate,
            id = id,
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