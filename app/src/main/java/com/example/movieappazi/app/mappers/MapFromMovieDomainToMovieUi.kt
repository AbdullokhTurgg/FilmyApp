package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.MovieDomain
import com.example.movieappazi.app.models.movie.MovieUi
import javax.inject.Inject

class MapFromMovieDomainToMovieUi @Inject constructor() : BaseMapper<MovieDomain, MovieUi> {
    override fun map(from: MovieDomain) = from.run {
        MovieUi(
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
            rating = rating!!,
            genre_ids = genre_ids?.map { id -> id },
        )
    }
}