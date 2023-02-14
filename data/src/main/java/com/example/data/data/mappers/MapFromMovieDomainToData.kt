package com.example.data.data.mappers

import com.example.data.data.models.movie.MovieData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import javax.inject.Inject

class MapFromMovieDomainToData @Inject constructor() : BaseMapper<MovieDomain, MovieData> {
    override fun map(from: MovieDomain) = from.run {
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