package com.example.data.data.mappers

import com.example.data.data.models.movie.MovieData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain

class MapFromListOfMovieDataToDomain(
    private val mapFromMovieDataToDomain: BaseMapper<MovieData, MovieDomain>,
) : BaseMapper<List<MovieData>, List<MovieDomain>> {
    override fun map(from: List<MovieData>): List<MovieDomain> = from.map {
        mapFromMovieDataToDomain.map(it)
    }
}