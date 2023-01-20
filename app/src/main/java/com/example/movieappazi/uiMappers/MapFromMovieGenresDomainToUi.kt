package com.example.movieappazi.uiMappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieGenresDomain
import com.example.movieappazi.uiModels.movie.MovieGenresUi

class MapFromMovieGenresDomainToUi : BaseMapper<MovieGenresDomain, MovieGenresUi> {
    override fun map(from: MovieGenresDomain) = from.run {
        MovieGenresUi(
            id = id, name = name
        )
    }
}