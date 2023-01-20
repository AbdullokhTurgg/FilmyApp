package com.example.data.network.cloud.cloudMappers.movie

import com.example.data.dataModel.movie.MovieData
import com.example.data.network.cloud.cloudModels.movie.MovieCloud
import com.example.domain.base.BaseMapper

class MapFromMovieCloudToData : BaseMapper<MovieCloud, MovieData> {
    override fun map(from: MovieCloud) = from.run {
        MovieData(
            posterPath = posterPath,
            adult = adult,
            overview = overview,
            releaseDate = releaseDate,
            id = id,
            originalTitle = originalTitle,
            originalLanguage = originalLanguage,
            title = title,
            backdropPath = backdropPath,
            popularity = popularity,
            voteCount = voteCount,
            video = video,
            rating = rating,
            genre_ids = genre_ids.map { id -> id },
        )
    }
}