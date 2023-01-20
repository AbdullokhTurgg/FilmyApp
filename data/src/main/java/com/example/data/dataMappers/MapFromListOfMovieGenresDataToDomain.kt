package com.example.data.dataMappers

import com.example.data.dataModel.movie.MovieGenresData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieGenresDomain

class MapFromListOfMovieGenresDataToDomain(
    private val mapFromMovieGenresDataToDomain: BaseMapper<MovieGenresData, MovieGenresDomain>,
) : BaseMapper<List<MovieGenresData>, List<MovieGenresDomain>> {
    override fun map(from: List<MovieGenresData>) = from.map {
        mapFromMovieGenresDataToDomain.map(it)
    }
}