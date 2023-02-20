package com.example.data.data.mappers

import com.example.data.data.models.movie.tv_shows.SeriesData
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.tv_shows.SeriesDomain
import javax.inject.Inject

class MapFromSeriesDomainToData @Inject constructor(): BaseMapper<SeriesDomain, SeriesData> {
    override fun map(from: SeriesDomain) = from.run {
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