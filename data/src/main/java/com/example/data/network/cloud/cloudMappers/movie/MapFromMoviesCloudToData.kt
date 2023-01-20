package com.example.data.network.cloud.cloudMappers.movie

import com.example.data.dataModel.movie.MovieData
import com.example.data.dataModel.movie.MoviesData
import com.example.data.network.cloud.cloudModels.movie.MovieCloud
import com.example.data.network.cloud.cloudModels.movie.MoviesCloud
import com.example.domain.base.BaseMapper

class MapFromMoviesCloudToData(
    private val mapFromMovieCloudToData: BaseMapper<List<MovieCloud>, List<MovieData>>,
) : BaseMapper<MoviesCloud, MoviesData> {
    override fun map(from: MoviesCloud) = from.run {
        MoviesData(page = page, movies = mapFromMovieCloudToData.map(movies), totalPage = totalPage)
    }
}