package com.example.movieappazi.uiMappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieGenresDomain
import com.example.movieappazi.uiModels.movie.MovieGenresUi

class MapFromListOfMovieGenresDomainToUi(
    private val mapFromMovieGenresDomainToUi: BaseMapper<MovieGenresDomain, MovieGenresUi>,
) : BaseMapper<List<MovieGenresDomain>, List<MovieGenresUi>> {
    override fun map(from: List<MovieGenresDomain>) = from.map {
        mapFromMovieGenresDomainToUi.map(it)
    }
}