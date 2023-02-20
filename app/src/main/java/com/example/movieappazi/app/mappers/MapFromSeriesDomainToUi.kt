package com.example.movieappazi.app.mappers

import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.tv_shows.SeriesDomain
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import javax.inject.Inject

class MapFromSeriesDomainToUi @Inject constructor() : BaseMapper<SeriesDomain, SeriesUi> {
    override fun map(from: SeriesDomain) = from.run {
        SeriesUi(
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