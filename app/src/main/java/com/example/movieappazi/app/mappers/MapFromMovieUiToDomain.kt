package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.movieappazi.app.models.movie.MovieUi
import javax.inject.Inject

class MapFromMovieUiToDomain @Inject constructor():BaseMapper<MovieUi, MovieDomain> {
    override fun map(from: MovieUi) = from.run {
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
             genre_ids = genre_ids,
         )
    }
}