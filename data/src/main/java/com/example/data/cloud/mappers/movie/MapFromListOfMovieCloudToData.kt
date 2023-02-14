package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.MovieCloud
import com.example.data.data.models.movie.MovieData
import com.example.domain.base.BaseMapper

class MapFromListOfMovieCloudToData(
    private val mapFromMovieCloudToData: BaseMapper<MovieCloud, MovieData>,
) : BaseMapper<List<MovieCloud>, List<MovieData>> {
    override fun map(from: List<MovieCloud>): List<MovieData> = from.map {
        mapFromMovieCloudToData.map(it)
    }
}