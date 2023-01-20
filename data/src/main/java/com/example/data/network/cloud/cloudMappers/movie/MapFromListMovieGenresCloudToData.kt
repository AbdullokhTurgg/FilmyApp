package com.example.data.network.cloud.cloudMappers.movie

import com.example.data.dataModel.movie.MovieGenresData
import com.example.data.network.cloud.cloudModels.movie.MovieGenresCloud
import com.example.domain.base.BaseMapper

class MapFromListMovieGenresCloudToData(
    private val mapFromMovieGenresCloudToData: BaseMapper<MovieGenresCloud, MovieGenresData>,
) : BaseMapper<List<MovieGenresCloud>, List<MovieGenresData>> {
    override fun map(from: List<MovieGenresCloud>) = from.map {
        mapFromMovieGenresCloudToData.map(it)
    }
}