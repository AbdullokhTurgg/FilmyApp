package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieGenresDomain
import com.example.movieappazi.app.models.movie.MovieGenresUi
import javax.inject.Inject

class MapFromMovieGenresDomainToUi @Inject constructor() : BaseMapper<MovieGenresDomain, MovieGenresUi> {
    override fun map(from: MovieGenresDomain) = from.run {
        MovieGenresUi(
            id = id, name = name
        )
    }
}