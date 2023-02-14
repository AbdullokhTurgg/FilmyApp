package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.MovieGenresCloud
import com.example.data.data.models.movie.MovieGenresData
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapFromListMovieGenresCloudToData @Inject constructor(
    private val mapFromMovieGenresCloudToData: BaseMapper<MovieGenresCloud, MovieGenresData>,
) : BaseMapper<List<MovieGenresCloud>, List<MovieGenresData>> {
    override fun map(from: List<MovieGenresCloud>) = from.map {
        mapFromMovieGenresCloudToData.map(it)
    }
}