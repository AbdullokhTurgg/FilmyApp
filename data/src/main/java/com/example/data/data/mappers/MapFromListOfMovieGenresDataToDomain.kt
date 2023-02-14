package com.example.data.data.mappers

import com.example.data.data.models.movie.MovieGenresData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieGenresDomain

class MapFromListOfMovieGenresDataToDomain(
    private val mapFromMovieGenresDataToDomain: BaseMapper<MovieGenresData, MovieGenresDomain>,
) : BaseMapper<List<MovieGenresData>, List<MovieGenresDomain>> {
    override fun map(from: List<MovieGenresData>) = from.map {
        mapFromMovieGenresDataToDomain.map(it)
    }
}