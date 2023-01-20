package com.example.movieappazi.uiMappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.movieappazi.uiModels.movie.MovieUi

class MapFromMovieUiToDomain:BaseMapper<MovieUi, MovieDomain> {
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