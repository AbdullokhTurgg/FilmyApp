package com.example.data.network.cloud.cloudMappers.movie

import com.example.data.dataModel.movie.MovieData
import com.example.data.network.cloud.cloudModels.movie.MovieCloud
import com.example.domain.base.BaseMapper

class MapFromListOfMovieCloudToData(
    private val mapFromMovieCloudToData: BaseMapper<MovieCloud, MovieData>,
) : BaseMapper<List<MovieCloud>, List<MovieData>> {
    override fun map(from: List<MovieCloud>): List<MovieData> = from.map {
        mapFromMovieCloudToData.map(it)
    }
}