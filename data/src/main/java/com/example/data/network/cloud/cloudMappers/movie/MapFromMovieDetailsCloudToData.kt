package com.example.data.network.cloud.cloudMappers.movie

import com.example.data.dataModel.movie.MovieDetailsData
import com.example.data.dataModel.movie.MovieGenresData
import com.example.data.network.cloud.cloudModels.movie.MovieDetailsCloud
import com.example.data.network.cloud.cloudModels.movie.MovieGenresCloud
import com.example.domain.base.BaseMapper

class MapFromMovieDetailsCloudToData(
    private val mapFromListMovieGenresCloudToData: BaseMapper<List<MovieGenresCloud>, List<MovieGenresData>>,
) : BaseMapper<MovieDetailsCloud, MovieDetailsData> {
    override fun map(from: MovieDetailsCloud) = from.run {
        MovieDetailsData(
            backdrop_path = backdrop_path,
            budget = budget,
            genres = mapFromListMovieGenresCloudToData.map(genres),
            id = id,
            originalLanguage = originalLanguage,
            originalTitle = originalTitle,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            releaseDate = releaseDate,
            runtime = runtime,
            status = status,
            title = title,
            video = video,
            voteAverage = voteAverage,
            voteCount = voteCount,
        )
    }
}