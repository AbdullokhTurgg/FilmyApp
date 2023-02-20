package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.MovieGenresDomain
import com.example.movieappazi.app.models.movie.MovieGenresUi

class MapFromListOfMovieGenresDomainToUi(
    private val mapFromMovieGenresDomainToUi: BaseMapper<MovieGenresDomain, MovieGenresUi>,
) : BaseMapper<List<MovieGenresDomain>, List<MovieGenresUi>> {
    override fun map(from: List<MovieGenresDomain>) = from.map {
        mapFromMovieGenresDomainToUi.map(it)
    }
}