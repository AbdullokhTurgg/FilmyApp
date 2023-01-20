package com.example.data.dataMappers

import com.example.data.dataModel.movie.MovieGenresData
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieGenresDomain

class MapFromMovieGenresDataToDomain : BaseMapper<MovieGenresData, MovieGenresDomain> {
    override fun map(from: MovieGenresData) = from.run {
        MovieGenresDomain(
            id = id,
            name = name
        )
    }
}