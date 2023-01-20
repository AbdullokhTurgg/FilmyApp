package com.example.data.dataMappers

import com.example.data.dataModel.movie.MovieData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain

class MapFromListOfMovieDataToDomain(
    private val mapFromMovieDataToDomain: BaseMapper<MovieData, MovieDomain>,
) : BaseMapper<List<MovieData>, List<MovieDomain>> {
    override fun map(from: List<MovieData>): List<MovieDomain> = from.map {
        mapFromMovieDataToDomain.map(it)
    }
}