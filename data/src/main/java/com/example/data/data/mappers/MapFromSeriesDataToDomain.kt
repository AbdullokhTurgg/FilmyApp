package com.example.data.data.mappers

import com.example.data.data.models.movie.tv_shows.SeriesData
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.tv_shows.SeriesDomain
import javax.inject.Inject

class MapFromSeriesDataToDomain @Inject constructor() : BaseMapper<SeriesData, SeriesDomain> {
    override fun map(from: SeriesData) = from.run {
        SeriesDomain(
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