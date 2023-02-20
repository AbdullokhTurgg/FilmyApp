package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.MovieDomain
import com.example.movieappazi.app.models.movie.MovieUi

class MapFromListOfMovieDomainToUi(
    private val mapFromMovieDomainToUi:BaseMapper<MovieDomain, MovieUi>
):BaseMapper<List<MovieDomain>, List<MovieUi>> {
    override fun map(from: List<MovieDomain>)= from.map {
        mapFromMovieDomainToUi.map(it)
    }
}