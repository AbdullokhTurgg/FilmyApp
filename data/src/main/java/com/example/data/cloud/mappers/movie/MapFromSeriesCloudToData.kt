package com.example.data.cloud.mappers.movie

import com.example.data.cloud.models.movie.tv_shows.SeriesCloud
import com.example.data.data.models.movie.tv_shows.SeriesData
import com.example.domain.base.BaseMapper
import javax.inject.Inject

class MapFromSeriesCloudToData @Inject constructor() : BaseMapper<SeriesCloud, SeriesData> {
    override fun map(from: SeriesCloud) = from.run {
        SeriesData(
            backdropPath = backdropPath,
            firstAirDate = firstAirDate,
            genreIds = genreIds.map { ids -> ids },
            id = id,
            name = name,
            originalLanguage = originalLanguage,
            originalName = originalName,
            overview = overview,
            popularity = popularity,
            posterPath = posterPath,
            voteAverage = voteAverage,
            voteCount = voteCount,
        )
    }
}