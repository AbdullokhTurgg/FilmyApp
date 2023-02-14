package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.MovieGenresCloud
import com.example.data.data.models.movie.MovieGenresData
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapMovieGenresCloudToData @Inject constructor(): BaseMapper<MovieGenresCloud, MovieGenresData> {
    override fun map(from: MovieGenresCloud) = from.run {
        MovieGenresData(
            id = id,
            name = name
        )
    }
}