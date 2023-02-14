package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.MovieCloud
import com.example.data.cloud.models.movie.MoviesCloud
import com.example.data.data.models.movie.MovieData
import com.example.data.data.models.movie.MoviesData
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapFromMoviesCloudToData @Inject constructor(
    private val mapFromMovieCloudToData: BaseMapper<List<MovieCloud>, List<MovieData>>,
) : BaseMapper<MoviesCloud, MoviesData> {
    override fun map(from: MoviesCloud) = from.run {
        MoviesData(
            page = page,
            movies = mapFromMovieCloudToData.map(movies),
            totalPage = totalPage,
        )
    }
}