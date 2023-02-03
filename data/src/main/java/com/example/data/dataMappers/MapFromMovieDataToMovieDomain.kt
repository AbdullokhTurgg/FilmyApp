package com.example.data.dataMappers

import com.example.data.dataModel.movie.MovieData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain

class MapFromMovieDataToMovieDomain : BaseMapper<MovieData, MovieDomain> {
    override fun map(from: MovieData) = from.run {
        MovieDomain(
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