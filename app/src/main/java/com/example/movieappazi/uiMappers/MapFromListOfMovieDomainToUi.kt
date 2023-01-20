package com.example.movieappazi.uiMappers

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.movieappazi.uiModels.movie.MovieUi

class MapFromListOfMovieDomainToUi(
    private val mapFromMovieDomainToUi:BaseMapper<MovieDomain, MovieUi>
):BaseMapper<List<MovieDomain>, List<MovieUi>> {
    override fun map(from: List<MovieDomain>)= from.map {
        mapFromMovieDomainToUi.map(it)
    }
}