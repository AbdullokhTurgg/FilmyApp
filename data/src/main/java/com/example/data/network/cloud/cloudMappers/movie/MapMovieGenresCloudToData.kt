package com.example.data.network.cloud.cloudMappers.movie

import com.example.data.dataModel.movie.MovieGenresData
import com.example.data.network.cloud.cloudModels.movie.MovieGenresCloud
import com.example.domain.base.BaseMapper

class MapMovieGenresCloudToData : BaseMapper<MovieGenresCloud, MovieGenresData> {
    override fun map(from: MovieGenresCloud) = from.run {
        MovieGenresData(
            id = id,
            name = name
        )
    }
}