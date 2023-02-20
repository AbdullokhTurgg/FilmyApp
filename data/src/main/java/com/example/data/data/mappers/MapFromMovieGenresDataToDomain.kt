package com.example.data.data.mappers

import com.example.data.data.models.movie.MovieGenresData
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.MovieGenresDomain
import javax.inject.Inject

class MapFromMovieGenresDataToDomain @Inject constructor()
    : BaseMapper<MovieGenresData, MovieGenresDomain> {
    override fun map(from: MovieGenresData) = from.run {
        MovieGenresDomain(
            id = id,
            name = name
        )
    }
}