package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.MovieDetailsCloud
import com.example.data.cloud.models.movie.MovieGenresCloud
import com.example.data.data.models.movie.MovieDetailsData
import com.example.data.data.models.movie.MovieGenresData
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapFromMovieDetailsCloudToData @Inject constructor(
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